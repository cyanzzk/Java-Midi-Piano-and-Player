# Java-Midi-Piano-and-Player
This is a mini java project that is used to simulate piano. It is also able to play .mid file.

You can directly run 'MidiPiano.jar'. Or by running code 'keyboard.java'.

I have also attached a demo video.

The keyboard should look like this:

<img width="712" alt="image" src="https://user-images.githubusercontent.com/62502750/209511423-1d588aeb-d6aa-46f8-b4fc-90dd398e5ac3.png">

The blue key is the middle C, corresponding to pitch 60.

<img width="715" alt="image" src="https://user-images.githubusercontent.com/62502750/209511481-fddad9bf-662b-43b1-ac36-08468a2e3a4d.png">

The play button is used to load a midi file and play it.

<img width="73" alt="image" src="https://user-images.githubusercontent.com/62502750/209511492-9a0c9947-0402-4e00-804e-e7365c5f7733.png">

The stop button is used to stop the music.

<img width="80" alt="image" src="https://user-images.githubusercontent.com/62502750/209511504-ccdef3b3-6c72-49a9-b72d-e6f1d5ed820e.png">


When playing a midi file, the user can simultaneously play notes.

To extend the keyboard, simply add numbers in the notes array (row 138 in the source code). Even number represents white key and odd number represents black key.
Tips: Pay attention to the key structure in real life. The key structure should be : White, Black, White, Black, White, White, Black, White, Black, White, Black, White.
<img width="703" alt="image" src="https://user-images.githubusercontent.com/62502750/209511529-87d9f89a-d49c-444e-83c0-fd10d4ede237.png">

