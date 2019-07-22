from os import environ

from NFS.client import MySQLClient


async def initialize(app, loop):
    db_settings = {
        "user": app.config["MYSQL_USERNAME"],
        "db": app.config["MYSQL_DBNAME"],
        "host": app.config["MYSQL_HOST"],
        "password": app.config["MYSQL_PASSWORD"],
        "autocommit": True,
    }

    await MySQLClient.init_db_settings(db_settings)


class Config:
    MYSQL_HOST = environ.get("NFS_MYSQL_HOST")
    MYSQL_USERNAME = environ.get("NFS_MYSQL_USERNAME")
    MYSQL_PASSWORD = environ.get("NFS_MYSQL_PASSWORD")
    MYSQL_DBNAME = environ.get("NFS_MYSQL_DBNAME")
