
from gtts import gTTS
import pyglet
from time import sleep
import speech_recognition as sr



def say(text):
  # send audio to google
  google_audio = gTTS(text)
  # save audio
  filename = "google_audio.wav"
  
  google_audio.save(filename)
  # play speech
  
  text = pyglet.media.load(filename, streaming=False)
  text.play()
  sleep(text.duration)
  return None


def record():
  r = sr.Recognizer()
  with sr.Microphone(device_index=1) as source:
    print("Start talking")
    audio = r.listen(source, phrase_time_limit = 5)

        
  filename = "D:\Coding\_tictactoe/audio_file.wav"
  with open(filename, "wb") as file:
      file.write(audio.get_wav_data())

  return filename


def get_audio():
  r = sr.Recognizer()
  with sr.Microphone(device_index=1) as source:
    print("Start talking")
    audio = r.listen(source, phrase_time_limit = 5)
  said = ""
  try:
      said = r.recognize_google(audio)
      print("You said: " + said)
  except Exception as e:
      print("Exception: " + str(e))

  return said.lower()

def repeat():
  words = get_audio()
  say(words)
  

print("Start")
# get_audio()

wake = "cat"

while True:
    print("Listening")
    start_text = get_audio()
    while start_text == wake:
      say("I am ready")
      text = get_audio()
      break

