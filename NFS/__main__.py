import asyncio
import uvloop

from NFS.app import create_app

if __name__ == "__main__":
    asyncio.set_event_loop_policy(uvloop.EventLoopPolicy())
    app = create_app()
    app.run(host="0.0.0.0", port=5000, debug=False)
