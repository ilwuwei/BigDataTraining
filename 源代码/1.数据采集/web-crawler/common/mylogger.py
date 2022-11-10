from config.setting import LogConfig
from datetime import datetime
from loguru import logger
from pathlib import Path
import sys


class MyLogger:
    """封装日志类"""

    @staticmethod
    def logHandler(file):
        """
        静态方法, 返回一个logger对象
        :param file: 调用者的文件名(日志文件保存的文件名): __file__
        :return:
        """
        log = logger

        log.remove()

        log.add(sys.stdout,
                format="<green>{time:YYYY-MM-DD HH:mm:ss}</green> | "  # 颜色>时间
                       "{process.name} | "  # 进程名
                       "{thread.name} | "  # 进程名
                       "{file} | "  # 进行日志记录调用的文件名
                       "<cyan>{module}</cyan>.<cyan>{function}</cyan>"  # 进行日志记录调用的模块名.方法名
                       ":<cyan>{line}</cyan> | "  # 行号
                       "<level>{level}</level>: "  # 等级
                       "<level>{message}</level>",  # 日志内容
                )

        # 输出到文件的格式,注释下面的add',则关闭日志写入
        log.add(f"{LogConfig.LOG_PATH}/{Path(file).stem}-{datetime.now().date()}.log",  # 日志保存的文件地址
                level='DEBUG',
                format='{time:YYYY-MM-DD HH:mm:ss} | '  # 时间
                       "{process.name} | "  # 进程名
                       "{thread.name} | "  # 进程名
                       "{file} | "  # 进行日志记录调用的文件名
                       '{module}.{function}:{line} | {level}: {message}',  # 进行日志记录调用的模块名.方法名:行号
                )

        return log
