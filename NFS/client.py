import aiomysql


class MySQLClient:
    __pool: aiomysql.Pool = None
    __db_settings: dict

    @classmethod
    async def init_db_settings(cls, db_settings: dict):
        cls.__db_settings = db_settings
        await cls.get_pool()

    @classmethod
    async def get_pool(cls) -> aiomysql.Pool:
        if cls.__pool and not cls.__pool._closed():
            pool = cls.__pool
        else:
            pool = aiomysql.create_pool(**cls.__db_settings)
        return pool

    @classmethod
    async def delete_pool(cls) -> None:
        if cls.__pool:
            cls.__pool.close()
            await cls.__pool.wait_closed()
        cls.__pool = None

    @classmethod
    async def execute(cls, sql: str, *args):
        pool = await cls.get_pool()
        async with pool.acquire() as conn:
            async with conn.cursor(aiomysql.DictCursor) as cur:
                affected_rows: int = await cur.execute(sql, args)
        return affected_rows
