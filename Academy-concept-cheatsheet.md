# Backend Spring Mastery — Concept Cheatsheet

> Baseline covering all completed academy material through Spring Security Lesson 2.
> Keep this terse: review aid, not lesson notes.

## Semester 1 — Spring Core

### IoC / Dependency Injection

- Spring owns object creation and dependency wiring for managed beans.
- Constructor injection is the default choice for required dependencies.
- Objects created manually with `new` are outside Spring lifecycle/DI unless explicitly integrated.

### Bean resolution

- `@Primary` selects a default candidate.
- `@Qualifier` narrows/selects by qualifier/name.
- Multiple matching beans without a resolvable choice cause startup failure.

### Bean lifecycle

- `BeanDefinition` = recipe/metadata; bean = managed instance.
- Constructor must succeed before post-processing / `@PostConstruct`.
- Spring-managed lifecycle does not apply to unmanaged objects.

### Bean scopes

- Singleton is default: one bean instance per application context.
- Other scopes change bean lifetime/visibility and should be chosen deliberately.

### `@Configuration` / `@Bean`

- `@Configuration` declares explicit bean configuration.
- `@Bean` registers the returned object as a Spring bean.

### Spring Boot configuration / auto-configuration

- Boot auto-configuration reacts to classpath and application/BeanFactory state.
- User-defined beans can make Boot defaults back off.
- Conditions decide whether an auto-configuration applies.

### Boot startup / embedded server

- Spring Boot assembles the application context, applies auto-configuration, then starts the embedded server.
- Startup failures are often bean-creation/configuration failures before the app can serve requests.

---

## Semester 2 — Spring MVC

### MVC request pipeline

- Typical path: `Tomcat → Filters → DispatcherServlet → HandlerMapping → HandlerAdapter → Controller`.
- Request-body path adds `HttpMessageConverter → Jackson → Bean Validation` before controller invocation.

### HTTP semantics

- `Content-Type` = representation sent by the client.
- `Accept` = representation the client wants back.
- Unsupported request representation → `415`.
- Cannot produce an acceptable response representation → `406`.

### DTO architecture

- Typical boundary: `Request DTO → Application Command → Domain → Repository abstraction → Persistence Entity → DB`.
- Do not expose persistence entities as public API contracts.

### Validation

- Malformed/type-invalid JSON fails during deserialization before Bean Validation.
- Bean Validation runs after successful deserialization.
- DB constraints remain the final integrity protection under concurrency.

### Exceptions

- Domain/application exceptions carry business context.
- Web layer maps them to HTTP/API responses.

### Jackson

- Serialization = Java → JSON.
- Deserialization = JSON → Java.

---

## Semester 3 — Persistence

### Relational DB / SQL / Transactions

- PK identifies rows; UNIQUE enforces uniqueness; FK preserves relationships.
- SQL uses three-valued logic with `NULL`.
- Application-side uniqueness checks are insufficient under concurrency; DB constraint is final protection.
- PostgreSQL failed statement inside a transaction leaves it aborted until rollback.
- `READ COMMITTED` uses a fresh committed snapshot per statement.
- `REPEATABLE READ` keeps a stable transaction snapshot.

### Indexes / planner

- Seq Scan reads table pages directly.
- Index Scan finds index entries then fetches rows.
- Bitmap Scan collects many row locations first, then fetches table pages efficiently.
- Index existence does not imply index usage; planner considers selectivity/cost/statistics.
- `EXPLAIN` = estimated plan; `EXPLAIN ANALYZE` executes and adds runtime data.
- Composite `(guild_id, level)`: `guild_id=?` good; `guild_id=? AND level>=?` best; `level>=?` weak.
- PostgreSQL does not automatically index the referencing FK column.

### JPA stack

- JPA = specification/API.
- Hibernate = JPA implementation / ORM engine.
- Spring Data JPA = repository abstraction on top.

### Entity states

- Transient, managed, detached, removed.
- Dirty checking persists changes to managed entities without requiring `save()`.
- Detached instances are not automatically tracked.

### Flush vs commit

- Flush synchronizes persistence-context changes to the DB.
- Commit finalizes the transaction.
- Flushed SQL can still be rolled back.

### `persist` vs `merge`

- `persist` makes the same transient instance managed.
- `merge` copies detached state into a managed instance and returns that managed instance.
- Original object passed to `merge` remains detached.

### First-level cache

- Within one persistence context, the same entity identity normally maps to the same Java object.

### Entity relationships

- Owning side controls the FK.
- `mappedBy` references the Java field on the owning side.
- Keep both Java sides synchronized in bidirectional associations.
- Lazy associations may be represented by proxies/references and trigger SQL on access.
- Lazy access after persistence-context closure can cause `LazyInitializationException`.
- Cascade propagates persistence operations; orphan removal has different lifecycle semantics.

### N+1 / query design

- N+1 = one main query plus repeated lazy-association queries.
- `EAGER` is not a guaranteed N+1 fix and can over-fetch.
- `JOIN` can constrain/query through an association without initializing it.
- `JOIN FETCH` initializes the association in that query.
- DTO projection is useful for read-only API shapes.
- Batch fetching groups lazy loads into fewer queries.
- Pagination + to-many fetch join is dangerous because SQL paginates rows, not distinct parents.

### Spring transactions

- `@Transactional` is normally applied by Spring proxy/interceptor.
- Self-invocation (`this.method()`) bypasses the proxy, so transactional advice on the inner method is not applied.
- `REQUIRED`: join existing transaction or create one.
- `REQUIRES_NEW`: suspend outer transaction and start independent transaction — only when call crosses the proxy.
- RuntimeException/Error → rollback by default.
- Checked exception → no rollback by default unless configured.
- Spring rollback-only means code may continue but final commit cannot succeed.
- PostgreSQL aborted transaction rejects subsequent SQL until rollback.
- Catching an exception does not necessarily clear rollback-only state.

### Concurrency / locking

- Lost update = two operations derive changes from the same stale starting state and one overwrites the other.
- `@Version` adds optimistic version checking to the SQL `UPDATE`.
- 0 updated rows on version check → optimistic-lock conflict.
- Retry the whole business operation from fresh state, not just the SQL statement.
- Pessimistic locking serializes conflicting work but adds waiting/latency/throughput/deadlock costs.
- Rare conflicts → optimistic is a good starting point; frequent contention may justify pessimistic locking.

---

## Semester 4 — Spring Security

### Lesson 1 — Request flow & core mental model

#### Request flow

- Security runs in the servlet filter chain before Spring MVC controllers.
- High-level flow:
  `Request → SecurityFilterChain → authentication → SecurityContext → authorization → DispatcherServlet → Controller`.

#### Authentication vs authorization

- Authentication = establish/verify identity.
- Authorization = decide whether that authenticated identity may perform an action.
- Missing/failed authentication → typically `401`.
- Authenticated but forbidden → typically `403`.

#### `Authentication`

- Represents current security identity/state, not the act of authenticating.
- Common data: principal, credentials, authorities, authenticated flag.
- Before authentication: unverified credential claim, often `authenticated=false`.
- After success: richer principal, granted authorities, `authenticated=true`.
- Authentication failure usually throws an `AuthenticationException`, rather than returning a normal false result.

#### `SecurityContext`

- Holds the current `Authentication`.
- Lets downstream code retrieve the current authenticated identity without reparsing credentials.

#### `GrantedAuthority`

- Generic permission/authority abstraction.
- Roles are a convention represented as authorities such as `ROLE_ADMIN`.
- `hasRole("ADMIN")` checks for `ROLE_ADMIN`.
- `hasAuthority("ADMIN")` checks literally for `ADMIN`.

#### Authentication pipeline

- `AuthenticationManager` = authentication entry point/coordinator.
- `AuthenticationProvider` = specialist for one authentication mechanism.
- `UserDetailsService` = loads user/security data for username/password auth.
- `PasswordEncoder` = verifies raw password against stored password hash.

#### Provider model

- `ProviderManager` typically delegates to the provider supporting the incoming `Authentication` type.
- New authentication mechanism should normally get its own provider rather than modifying unrelated providers.

#### Password hashing / bcrypt

- bcrypt is one-way password hashing, not encryption.
- bcrypt stores salt/cost information in the encoded hash.
- Re-encoding the same raw password usually produces a different string because a new salt is generated.
- `matches(raw, storedHash)` reuses the salt/cost encoded in the stored hash to verify the candidate.
- bcrypt is deliberately expensive to slow brute-force attacks.

#### 401 / 403 handling

- Authentication failure → `AuthenticationException` → `AuthenticationEntryPoint` → `401`.
- Authorization failure → `AccessDeniedException` → `ExceptionTranslationFilter` → `AccessDeniedHandler` → `403`.
- `ExceptionTranslationFilter` translates security exceptions into HTTP/security handling.

#### Matcher safety

- Request matchers are evaluated by matching rules; a typo can make a sensitive endpoint fall through to a weaker fallback.
- Prefer explicit rules with a fail-closed fallback when appropriate: `.anyRequest().denyAll()`.
- Test critical authorization paths explicitly: anonymous→401, wrong role→403, correct role→200.

#### Spring Security configuration

- `Customizer.withDefaults()` enables a feature with default customization.
- `@EnableWebSecurity` enables/imports servlet web-security infrastructure; Spring Boot can auto-integrate much of this.
- A custom `SecurityFilterChain` bean defines the application’s HTTP security rules.

---

### Lesson 2 — Stateful, stateless, CSRF & JWT

#### Stateful session authentication

- Successful authentication creates an `Authentication` in the `SecurityContext`.
- The `SecurityContext` can be persisted inside the server-side HTTP session.
- `JSESSIONID` identifies the HTTP session — not the SecurityContext directly.
- Later requests can send `JSESSIONID`; Spring restores the session’s SecurityContext instead of rechecking the password.
- Session storage may be memory, Redis, DB-backed session storage, etc.

#### Stateless authentication

- Stateless means each request authenticates independently and does not rely on per-client authentication state from a previous request.
- A `SecurityContext` still exists during the current request.
- With `SessionCreationPolicy.STATELESS`, Spring Security does not persist the authentication in an HTTP session for later reuse.
- The next request must present credentials/token again.
- Stateless authentication does not mean the application has no state; DB, cache, Kafka offsets, etc. can still exist.

#### CSRF

- CSRF = Cross-Site Request Forgery.
- Main risk: browsers automatically attach cookies/session credentials to matching requests.
- An attacker may trigger a state-changing request even if they cannot read the response.
- Session cookie identifies the authenticated session; CSRF token adds an unpredictable value the attacker should not be able to obtain.
- CORS and CSRF are different:
  - CSRF protects against forged authenticated side effects.
  - CORS controls browser JavaScript access across origins.
- Cookie-based authentication can require CSRF protection; bearer-token APIs often make a different CSRF tradeoff.

#### JWT structure

- JWT shape: `header.payload.signature`.
- Header contains metadata such as signing algorithm.
- Payload contains claims such as `sub`, `exp`, `iss`, `aud`, roles/permissions.
- Signed JWT payloads are generally readable; signing does not provide confidentiality.
- Never put secrets such as passwords in normal JWT claims.

#### JWT trust model

- A JWT is not trusted just because it has a signature field.
- Validate first, then trust claims.
- Typical sequence:
  `receive JWT → verify signature → check expiration → validate issuer/audience → trust claims`.
- Signature establishes:
  - integrity: signed contents were not modified;
  - authenticity: signed by someone holding the trusted signing key.
- Signature validity alone does not prove `exp`, `iss`, `aud`, or other claim requirements are valid.

#### JWT authentication flow

- Typical flow:
  `Bearer token → filter → AuthenticationManager → JwtAuthenticationProvider → JwtDecoder/validation → authenticated Authentication → SecurityContext → authorization`.
- A valid JWT can carry enough trusted identity/authority data to avoid a DB lookup on every request.
- DB lookup/revocation/fresh-permission checks are application design choices.

#### Symmetric signing — HS256

- Same shared secret signs and verifies.
- Every resource server with the secret can technically mint valid tokens.
- If one verifier leaks the shared secret, every service trusting that secret can be affected.

#### Asymmetric signing — RS256

- Private key signs; public key verifies.
- Only the trusted issuer should hold the private signing key.
- Resource servers receive the public key and can verify but cannot forge valid signatures.
- Compromising a verifier’s public key does not grant signing capability.
- RS256 provides authenticity/integrity, not payload confidentiality.

---

## Known foundations to keep reinforcing

### Compile time vs runtime

- Compile time: source is checked/transformed into executable artifacts/bytecode.
- Runtime: the already-built program is executing.
- Keep distinguishing dependency availability during compilation from what is present/used when the program runs.

### Build/classpath awareness

- Maven dependency scopes and classpaths affect when dependencies are available.
- Compilation, packaging, dependency resolution, and runtime loading are different stages.

### Lesson 3 — Method Security & Authorization Design

#### URL security vs method security

- Request matchers protect HTTP routes before controller execution.
- Method security protects invocation of application/service methods.
- Method security can protect an operation even when called from another entry point.

#### Enable method security

```java
@EnableMethodSecurity
```

- Enables Spring Security method interception for annotations such as `@PreAuthorize`.
- Method-security annotations rely on Spring proxy/interceptor behavior.

#### `@PreAuthorize`

```java
@PreAuthorize("hasRole('ADMIN')")
```

- Evaluated before the method executes.
- Can use:
  - current `Authentication`;
  - method arguments such as `#orderId`;
  - authorization helpers such as `hasRole(...)`;
  - other Spring beans.

Example:

```java
@PreAuthorize(
    "hasRole('ADMIN') or @orderAuthorization.canEdit(#orderId, authentication)"
)
```

#### Authorization components

- Keep complex authorization policy in normal Java components rather than long annotation expressions.
- Benefits:
  - readability;
  - unit testing;
  - maintainability;
  - isolation of authorization policy.

Example:

```java
@Component
class OrderAuthorization {

    boolean canEdit(UUID orderId, Authentication authentication) {
        ...
    }
}
```

#### Authorization vs domain invariants

- Authorization policy asks: **may this actor perform this operation?**
- Domain rule asks: **is this operation valid for the current business state?**

Example:

```text
Owner or admin may edit order
→ authorization

Cancelled order cannot be edited
→ domain invariant
```

- Domain code should generally remain unaware of Spring Security types such as `Authentication` and `SecurityContext`.

#### Resource-dependent authorization

- `@PreAuthorize` is useful when authorization can be evaluated cheaply before method execution.
- If the resource must be loaded anyway, it can be cleaner to:

```text
load resource
→ authorization component checks loaded resource
→ execute domain behavior
```

instead of:

```text
authorization DB query
→ method starts
→ resource DB query
```

- Method-security annotations are a tool, not a requirement for every authorization rule.

#### Self-invocation

- Method security relies on calls crossing the Spring proxy.
- Internal calls such as:

```java
this.adminOnly();
```

do not cross the proxy.

Therefore:

```java
public void outer() {
    adminOnly();
}

@PreAuthorize("hasRole('ADMIN')")
public void adminOnly() {
}
```

can bypass the authorization interceptor.

Mental model:

```text
external caller
→ Spring proxy
→ interceptor
→ secured method
```

versus:

```text
same bean
→ this.securedMethod()
→ proxy bypassed
→ annotation not evaluated
```

#### `@PostAuthorize`

- Evaluated after the method executes.
- Can use:

```text
returnObject
```

which represents the returned value.

Example:

```java
@PostAuthorize(
    "returnObject.ownerId == authentication.name"
)
```

- Useful mainly for read-style operations where authorization depends on returned data.
- Dangerous for writes because side effects may already have happened before authorization fails.

#### `@PreFilter` / `@PostFilter`

- `@PreFilter` filters collection input before method execution.
- `@PostFilter` filters collection output after method execution.
- `filterObject` represents the current collection element.

Be careful with large datasets:

```text
load huge dataset
→ filter in Java
```

may be much worse than:

```text
filter directly in DB query
```

#### Method-security testing

Two different things need testing:

```text
Authorization component unit tests
→ is the policy logic correct?

Method-security integration tests
→ does Spring actually enforce the policy?
```

Integration tests should prove:

- method security is enabled;
- proxy/interceptor is active;
- annotation expression resolves;
- referenced bean names are correct;
- method parameters resolve correctly;
- allowed callers succeed;
- forbidden callers are rejected.

#### Security test levels

### Unit test

Use for pure authorization/business policy logic.

Example:

```text
OrderAuthorization.canEdit(...)
```

Proves:

```text
authorization rule itself is correct
```

Does NOT prove Spring Security wiring.

---

### Method-security integration test

Test a Spring-managed service containing:

```java
@PreAuthorize(...)
```

Proves:

```text
Spring proxy/interceptor is active
@PreAuthorize is evaluated
SpEL parameters/beans resolve
allowed users pass
forbidden users are rejected
```

The service must come from the Spring context:

```java
@Autowired
SecurityLabService securityLabService;
```

not:

```java
new SecurityLabService();
```

---

### MockMvc security test

Use for HTTP-level security.

Proves:

```text
HTTP request
→ SecurityFilterChain
→ request matcher
→ authorization
→ expected HTTP status
```

Typical matrix:

```text
anonymous → 401
wrong role → 403
correct role → 200
```

Useful for catching:

- matcher typos;
- wrong role configuration;
- unsafe fallback rules;
- forgotten route protection.

### Lesson 5 — OAuth2 Resource Server & Real JWT Configuration

#### OAuth2 / Access Token / JWT

- OAuth2 = authorization framework.
- Access token = credential presented by a client to a Resource Server.
- JWT = one possible format for an access token.
- Not every JWT is an OAuth2 access token, and not every OAuth2 access token is a JWT.

#### Authorization Server vs Resource Server

- Authorization Server:
  - authenticates/authorizes according to OAuth2 flow;
  - issues access tokens;
  - owns signing capability.
- Resource Server:
  - exposes protected APIs/resources;
  - validates access tokens;
  - authorizes access.

#### JWT Resource Server flow

```text
Bearer token
→ Security filter
→ AuthenticationManager
→ JwtAuthenticationProvider
→ JwtDecoder
→ validated Jwt
→ authenticated Authentication
→ SecurityContext
→ authorization
```

#### `JwtDecoder`

- Parses JWT.
- Verifies signature.
- Validates claims such as expiration/issuer/audience.
- Returns trusted JWT data.

#### `JwtAuthenticationProvider`

- Handles JWT authentication.
- Uses `JwtDecoder`.
- Converts validated JWT data into authenticated Spring Security `Authentication`.

#### OAuth2 scopes

Spring maps OAuth2 scopes by default to authorities prefixed with:

```text
SCOPE_
```

Example:

```text
scope = admin
→ SCOPE_admin
```

Use:

```java
hasAuthority("SCOPE_admin")
```

Not:

```java
hasRole("SCOPE_admin")
```

because:

```text
hasRole("SCOPE_admin")
→ checks ROLE_SCOPE_admin
```

#### Failure behavior

```text
valid JWT + missing authority
→ authentication succeeds
→ authorization fails
→ 403

expired JWT
→ authentication fails
→ 401

invalid signature
→ authentication fails
→ 401
```

#### HS256 vs RS256

HS256:

- shared secret signs and verifies;
- Resource Server possessing the secret can also sign tokens;
- leaking one shared secret can enable token forgery.

RS256:

- private key signs;
- public key verifies;
- Resource Servers only need the public key;
- compromising a Resource Server does not reveal signing capability.

#### Local JWT validation

Advantages:

- avoids an auth-server network call on every request;
- lower latency;
- less load on Authorization Server;
- better resilience if Authorization Server is temporarily unavailable.

Tradeoff:

- token claims can become stale;
- revoked permissions/account state may remain effective until token expiry or another revocation strategy applies.
