const axios = require('axios');
const fs = require('fs');

const url = 'https://edu.gplweb.pl/sh/';
const filePath = 'Files/File';

axios({
  method: 'get',
  url: url,
  responseType: 'stream'
})
.then((response) => {
  const writer = fs.createWriteStream(filePath);

  response.data.pipe(writer);

  writer.on('finish', () => {
    console.log('Pobieranie zakończone.');
  });

  writer.on('error', (err) => {
    console.error(`Błąd podczas zapisywania pliku: ${err.message}`);
  });
})
.catch((error) => {
  console.error(`Błąd pobierania: ${error.message}`);
});
