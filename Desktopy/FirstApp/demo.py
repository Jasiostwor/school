import sys

from PySide6.QtWidgets import QApplication, QWidget
from PySide6.QtUiTools import QUiLoader

class demo(QWidget):
    def __init__(self):
        super().__init__()

        loader = QUiLoader()
        self.win = loader.load("dapp.ui", None)
        self.win.show()

if __name__ == '__main__':
    app = QApplication(sys.argv)

    win = demo()

    try:
        sys.exit(app.exec_())
    except SystemExit:
        print("App End")