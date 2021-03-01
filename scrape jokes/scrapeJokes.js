const axios = require('axios');
const { JSDOM } = require('jsdom');

const BASE = 'https://www.ajokeaday.com/search?filterType=winnerOnly&sortingType=mostvotes'

async function scrapeJokePage(catid, pageLim){

    let jokes = [];

    for(let i = 1; i <= pageLim; i++){

        const { data } = await axios(`${BASE}&page=${i}&catid=${catid}`);
        const { window: { document } } = new JSDOM(data);
        jokes.push(...Array.from(document.querySelectorAll`.jd-maincontainer .jd-body`, e => e.textContent.trim().split`\n`.filter(n => n.length)).map(n => ({ punchline: n.pop(), setup: n.join`\n`, category: { id: catid } })));
    }

    await Promise.all(
        jokes.map(joke => axios.post('http://localhost:8080/api/jokes', joke)),
    );
}

scrapeJokePage(13, 15);