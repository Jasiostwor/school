import sys
from PyQt5.QtWidgets import QApplication, QWidget

from PyQt5 import uic
#.QtUiTools import QUiLoader

class DemoApp(QWidget):
	def __init__(self):
		super().__init__()
		uic.loadUi("untitled.ui",self)
		
	def readOpen(self):
		print('nacisnieto open')
		
	def readSave(self):
		print('nacisnieto save')
		
	def readDuplicate(self):
		print('nacisnieto duplicate')
		
	def readDelete(self):
		print('nacisnieto del')			
		
	def duplicateEntry(self):
		print('asd')	
	
	def removeEntry(self):
		print('asd')	
		
	def openSettings(self):
		print('asd')	
		
	def toggleHelpPanel(self):
		print('asd')			
		
	def ParseIntoList(self):
		print('asd')	
		
	def readEntry(self):
		print('asd')	
		
	def switchTab(self):
		print('asd')	
	
	def updateStatus(self):
		print('asd')			

if __name__ == '__main__':
	app = QApplication(sys.argv)
	win = DemoApp()
	win.show()

	sys.exit(app.exec_())

