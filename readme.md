# Android Word Scrapper
This is a task that was required for the instabug internship program 2020 that did not pass the task phase despite being good enough to pass. It was probably because I didn't write test cases.  

## Required
In a five-hour period, develop an android app that scrapes the instabug website and list every word on their home page and how many times each word occured **(Without the use of third-party libraries)**.

## Tools I used
* #### Java  
    I would personally had chosen Kotlin but instabug seems to be stuck with java for eternity.  
    <br />

* #### WorkManager
    For handling threading, network calls, processing of scraped web page, and storing processed words to database.  
    <br />

 * #### Room and LiveData
    For database and observing stored words in the UI.  
    <br />

 * #### java.net.HttpUrlConnection
    As no third-party tools were allowed, I had to use the java.net package to handle the network call to the instabug website. 


## Architecture     
MVVM architecture was used
![architecture| width=100](https://i.imgur.com/JzlhGOw.png)