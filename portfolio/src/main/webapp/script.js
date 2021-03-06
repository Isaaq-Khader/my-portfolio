// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random fact to the page.
 */
function addRandomFact() {
  const facts =
      ['I like to rock climb!', 'I play Overwatch', 'I like to play guitar', 'I am Indian and Mexican!'];

  // Pick a random fact.
  const fact = facts[Math.floor(Math.random() * facts.length)];

  // Add it to the page.
  const factContainer = document.getElementById('fact-container');
  factContainer.innerText = fact;
}


async function sayHello() 
{
    const responseFromServer = await fetch("/greetings");
    const textFromResponse = await responseFromServer.text();

    const helloContainer = document.getElementById('hello-container');
    helloContainer.innerText = textFromResponse;
}

function RNG(max) {
  return Math.floor(Math.random() * Math.floor(max));
}

/** Gets favorite anime by random from the server and adds it to the page. */
async function giveAnime() 
{
    const responseFromServer = await fetch("/anime");
    // The json() function returns an object that contains fields that we can
    // reference to create HTML.
    const animes = await responseFromServer.json();

    const anime = animes[RNG(8)];

    
    const animeContainer = document.getElementById('anime-container');
    animeContainer.innerHTML = '';

    animeContainer.appendChild(
      createListElement('Anime Name: ' + anime.name));
    animeContainer.appendChild(
      createListElement('My Thoughts: ' + anime.description));
    animeContainer.appendChild(
      createListElement('Rating: ' + anime.rating));
}

/** Creates an <li> element containing text. */
function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}