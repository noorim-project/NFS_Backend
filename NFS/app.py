from sanic import Sanic


def create_app() -> Sanic:
    _app = Sanic("NFS_Backend")
    return _app
