# Backend Spring Mastery — Terminal Cheatsheet

> Baseline covering commands encountered through Spring Security Lesson 2.

## PostgreSQL

### Connect to the learning database

```bash
psql -h localhost -U backend_lab -d backend_learning
```

- `-h localhost` → PostgreSQL host.
- `-U backend_lab` → database user.
- `-d backend_learning` → database name.

---

## HTTP / curl

### Include HTTP response headers

```bash
curl -i http://localhost:8080/api/security-lab/hello
```

- `-i` → include HTTP response headers in the output.

---

### Send HTTP Basic credentials

```bash
curl -i \
  -u user:password \
  http://localhost:8080/api/security-lab/hello
```

- `-u user:password` → send HTTP Basic credentials.

Conceptually, curl sends an HTTP header similar to:

```http
Authorization: Basic <encoded-credentials>
```

---

### Save response cookies to a file

```bash
curl -i \
  -c cookies.txt \
  http://localhost:8080/login
```

- `-c cookies.txt` → save cookies received from the server into `cookies.txt`.

This is useful for session-based authentication experiments.

---

### Send previously stored cookies

```bash
curl -i \
  -b cookies.txt \
  http://localhost:8080/api/security-lab/hello
```

- `-b cookies.txt` → read cookies from `cookies.txt` and send them with the request.

---

### Send and update the same cookie jar

```bash
curl -i \
  -b cookies.txt \
  -c cookies.txt \
  http://localhost:8080/some-endpoint
```

- `-b cookies.txt` → send existing cookies.
- `-c cookies.txt` → save any new or updated cookies back to the file.

---

### Submit form data

```bash
curl -i \
  -b cookies.txt \
  -c cookies.txt \
  -X POST \
  -d "username=user" \
  -d "password=password" \
  -d "_csrf=<TOKEN>" \
  http://localhost:8080/login
```

- `-X POST` → explicitly use the HTTP `POST` method.
- `-d "key=value"` → send form-encoded request data.
- `-b cookies.txt` → send the existing session cookie.
- `-c cookies.txt` → persist updated cookies.
- `_csrf=<TOKEN>` → send the CSRF token expected by Spring Security.

---

## Session-based authentication experiment

### Get a session and CSRF token

Example request:

```bash
curl -i \
  -c cookies.txt \
  http://localhost:8080/api/security-lab/csrf
```

This can return:

```http
Set-Cookie: JSESSIONID=...
```

The cookie identifies the HTTP session.

---

### Login using the existing session

```bash
curl -i \
  -b cookies.txt \
  -c cookies.txt \
  -X POST \
  -d "username=user" \
  -d "password=password" \
  -d "_csrf=<TOKEN>" \
  http://localhost:8080/login
```

A successful form login typically returns a redirect such as:

```text
302
```

---

### Reuse the authenticated session

```bash
curl -i \
  -b cookies.txt \
  http://localhost:8080/api/security-lab/hello
```

No `-u user:password` is required here.

The server can restore:

```text
JSESSIONID
→ HTTP session
→ SecurityContext
→ Authentication
```

---

## Stateless authentication experiment

### Authenticate one request

```bash
curl -i \
  -u user:password \
  -c cookies-stateless.txt \
  http://localhost:8080/api/security-lab/hello
```

Expected:

```text
200
```

The request is authenticated because credentials were supplied.

---

### Try another request without credentials

```bash
curl -i \
  -b cookies-stateless.txt \
  http://localhost:8080/api/security-lab/hello
```

With:

```java
SessionCreationPolicy.STATELESS
```

expected:

```text
401
```

The previous `Authentication` is not persisted for reuse.

---

## Spring Security response checks

### Protected endpoint without authentication

```bash
curl -i \
  http://localhost:8080/api/security-lab/hello
```

Typical response:

```text
401
```

Often accompanied by:

```http
WWW-Authenticate: Basic realm="Realm"
```

---

### Protected endpoint with valid Basic credentials

```bash
curl -i \
  -u user:password \
  http://localhost:8080/api/security-lab/hello
```

Typical response:

```text
200
```

---

### Authenticated user without required role

```bash
curl -i \
  -u user:password \
  http://localhost:8080/api/security-lab/admin
```

Typical response:

```text
403
```

Reason:

```text
authenticated
but missing ROLE_ADMIN
```

---

### Authenticated admin

```bash
curl -i \
  -u admin:admin \
  http://localhost:8080/api/security-lab/admin
```

Typical response:

```text
200
```

---

### Security allows request but no MVC endpoint exists

```bash
curl -i \
  -u user:password \
  http://localhost:8080/api/security-lab/unknown
```

Typical response:

```text
404
```

Security allowed the request, but Spring MVC could not find a controller mapping.

---

## Authentication failure

### Wrong password

```bash
curl -i \
  -u user:wrong-password \
  http://localhost:8080/api/security-lab/hello
```

Typical response:

```text
401
```

Conceptually:

```text
bad credentials
→ AuthenticationException
→ AuthenticationEntryPoint
→ 401
```

---

## File inspection

### Print a file in the terminal

```bash
cat cookies.txt
```

Useful for quickly inspecting cookie jars or generated text files.

---

### Open a file in Neovim

```bash
nvim cookies.txt
```

Useful when you want to inspect or edit the file interactively.

---

## Useful HTTP status reminders

```text
200
→ request succeeded

401
→ authentication missing or failed

403
→ authenticated but not authorized

404
→ security allowed the request, but no matching application endpoint exists
```

---

## Maven / Java

No new Maven or Java CLI commands were introduced during the completed Spring Security lessons yet.
