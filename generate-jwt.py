import jwt
import time

with open("private.pem", "r") as key_file:
    private_key = key_file.read()

now = int(time.time())

payload = {"sub": "gauthier", "scope": "admin", "iat": now - 7200, "exp": now - 3600}


token = jwt.encode(payload, private_key, algorithm="RS256")

print(token)
