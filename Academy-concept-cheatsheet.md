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
