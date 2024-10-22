import sys
import os
import subprocess
from PyQt5.QtWidgets import QApplication, QMainWindow, QPushButton, QTableWidget, QVBoxLayout, QHBoxLayout, QTableWidgetItem, QWidget, QFileDialog, QAbstractItemView, QMessageBox
from PyQt5.QtCore import QMimeData, QUrl
from PyQt5.QtGui import QGuiApplication, QIcon,QDragEnterEvent, QDropEvent

class ClipClapApp(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Clip-Clap")
        self.setGeometry(100, 100, 600, 400)
        
        # Główna zawartość
        main_widget = QWidget()
        self.setCentralWidget(main_widget)
        
        # Layout główny
        main_layout = QVBoxLayout()
        main_widget.setLayout(main_layout)
        
        # Górny pasek przycisków
        top_layout = QHBoxLayout()
        self.btn_browse = QPushButton("Przeglądaj katalog")
        self.btn_save_txt = QPushButton("Zapisz TXT")
        top_layout.addWidget(self.btn_browse)
        top_layout.addWidget(self.btn_save_txt)
        main_layout.addLayout(top_layout)
        
        # Tabela z zawartością plików
        self.table = QTableWidget(0, 3)
        self.table.setHorizontalHeaderLabels(["Icon", "Plik", "Ścieżka"])
        self.table.setSelectionBehavior(QAbstractItemView.SelectRows)
        self.table.setEditTriggers(QAbstractItemView.NoEditTriggers)
        self.table.horizontalHeader().setStretchLastSection(True)
        main_layout.addWidget(self.table)
        
        # Przycisk funkcji
        bottom_layout = QHBoxLayout()
        self.btn_use_copy = QPushButton("Użyj/Kopiuj")
        self.btn_open_folder = QPushButton("Otwórz katalog")
        self.btn_delete = QPushButton("Usuń z listy")
        bottom_layout.addWidget(self.btn_use_copy)
        bottom_layout.addWidget(self.btn_open_folder)
        bottom_layout.addWidget(self.btn_delete)
        main_layout.addLayout(bottom_layout)
        
        # Podpięcie akcji
        self.btn_browse.clicked.connect(self.browse_folder)
        self.btn_save_txt.clicked.connect(self.save_txt)
        self.btn_use_copy.clicked.connect(self.use_copy)
        self.btn_open_folder.clicked.connect(self.open_folder)
        self.btn_delete.clicked.connect(self.delete_from_list)

        self.setAcceptDrops(True) 

    def dragEnterEvent(self, event: QDragEnterEvent):
        if event.mimeData().hasUrls():
            urls = event.mimeData().urls()
            if os.path.isdir(urls[0].toLocalFile()):
                event.acceptProposedAction()
            else:
                event.ignore()
        else:
            event.ignore()

    def dropEvent(self, event: QDropEvent):
        urls = event.mimeData().urls()
        if urls:
            folder = urls[0].toLocalFile()
            if os.path.isdir(folder):
                self.load_files(folder)

    def browse_folder(self):
        folder = QFileDialog.getExistingDirectory(self, "Wybierz katalog")
        if folder:
            self.load_files(folder)

    def load_files(self, folder):
        self.table.setRowCount(0)

        items = os.listdir(folder)
        
        self.table.setRowCount(len(items))
        for i, item in enumerate(items):
            item_path = os.path.join(folder, item)
            
            icon_item = QTableWidgetItem()
            
            if os.path.isdir(item_path):
                icon_item.setIcon(self.style().standardIcon(self.style().SP_DirIcon))
            else:
                icon_item.setIcon(self.style().standardIcon(self.style().SP_FileIcon))
            
            self.table.setItem(i, 0, icon_item)
            self.table.setItem(i, 1, QTableWidgetItem(item))
            self.table.setItem(i, 2, QTableWidgetItem(item_path))
    
    def save_txt(self):
        path, _ = QFileDialog.getSaveFileName(self, "Zapisz TXT", "", "TXT Files (*.txt)")
        if path:
            with open(path, "w") as file:
                for row in range(self.table.rowCount()):
                    file.write(self.table.item(row, 1).text() + "\n")
            QMessageBox.information(self, "Zapisano", "Plik został zapisany.")
    
    def use_copy(self):
        selected_row = self.table.currentRow()
        if selected_row >= 0:
            file_path = self.table.item(selected_row, 2).text()
            
            self.copy_file_to_clipboard(file_path)

            QMessageBox.information(self, "Skopiowano", f"Plik został skopiowany do schowka:\n{file_path}")
    
    def copy_file_to_clipboard(self, file_path):
        mime_data = QMimeData()
        url = QUrl.fromLocalFile(file_path)
        mime_data.setUrls([url])

        clipboard = QGuiApplication.clipboard()
        clipboard.setMimeData(mime_data)


    def open_folder(self):
        selected_row = self.table.currentRow()
        if selected_row >= 0:
            file_path = self.table.item(selected_row, 2).text()
            folder_path = os.path.dirname(file_path)

            if sys.platform == "win32":
                os.startfile(folder_path)
            elif sys.platform == "darwin":
                subprocess.run(["open", folder_path])
            else:
                subprocess.run(["xdg-open", folder_path])
    
    def delete_from_list(self):
        selected_row = self.table.currentRow()
        if selected_row >= 0:
            self.table.removeRow(selected_row)
            QMessageBox.information(self, "Usunięto", "Plik został usunięty z listy.")
    
if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = ClipClapApp()
    window.show()
    sys.exit(app.exec_())
