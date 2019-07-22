from sanic.views import HTTPMethodView
from werkzeug.security import generate_password_hash

from NFS.client import MySQLClient


class UserSignUp(HTTPMethodView):
    def post(self, request):
        email = request.json["email"]
        user_id = request.json["user_id"]
        password = generate_password_hash(request.json["password"])


class UserSignUpVerify(HTTPMethodView):
    def get(self, request):
        ...


class UserToken(HTTPMethodView):
    def get(self, request):
        ...

    def post(self, request):
        ...

    def delete(self, request):
        ...
