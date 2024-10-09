document.addEventListener('DOMContentLoaded', function() {
    const codeLengthSelect = document.getElementById('codeLength');
    const generateBtn = document.getElementById('generateBtn');
    const codeDisplay = document.getElementById('codeDisplay');

    generateBtn.addEventListener('click', function() {
        const length = parseInt(codeLengthSelect.value);
        try {
            const codeGenerator = new CodeGenerator(length);
            const code = codeGenerator.generateCode();
            codeDisplay.textContent = code;
        } catch (error) {
            codeDisplay.textContent = error.message;
        }
    });
});

class CodeGenerator {
    constructor(length) {
        this.length = length;
        this.characters = '';
        if (length === 4 || length === 6) {

            this.characters = '0123456789';
        } else if (length === 12 || length === 16) {
            this.characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
        } else {
            throw new Error('Nieprawidłowa długość kodu');
        }
    }

    generateCode() {
        let code = '';
        for (let i = 0; i < this.length; i++) {
            const randomIndex = Math.floor(Math.random() * this.characters.length);
            code += this.characters[randomIndex];
        }
        return code;
    }
}
