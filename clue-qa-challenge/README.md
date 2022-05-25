# Clue QA Challenge by Ivan Skvortsov

Initial task can be found [here](TASK.md) .

## Tools

### [Kakao](https://github.com/KakaoCup/Kakao)

It's a real nice framework that reduces clutter in tests and makes your Screen Object classes look neat and clean. 
It also helps that Kakao has built-in wait (5 sec timeout, if I'm not mistaken) and using it without any additions is perfectly fine for a project with local/mocked server. 

Other than that, I didn't use any external tools.

### Pattern - Screen Object

I usually pick this as it makes sense to have all screen Views and actions with them at one place. Kakao helps with this a lot and makes the code even more straightforward and clean.

It was also possible to use Robot pattern, but it's not always the best choice and can make the test framework unnecessarily complex (personal experience)

## Possible improvements

It's possible to simulate no connection and bad connection for this app and check corresponding errors described in [strings.xml - lines 19-21](app/src/main/res/values/strings.xml)

If there is a plan to use "real" remote server in the future, it would make sense to implement IdlingResourse to wait for server response.

It might be a good idea to implement [Kaspresso](https://github.com/KasperskyLab/Kaspresso) for better UI test logs and test structure and faster execution.

If the project calls for BDD - I would use [Cucumber](https://cucumber.io) along with Kakao.

If there will be lots of tests in the future, it would be nice to run them in parallel on some cloud service like Firebase Testlab, and I would use [Flank/Fladle](https://github.com/runningcode/fladle) to be able to run those tests in parallel.

In other words, there are many possibilities, but too little time... :) 

### Some feedback regarding the test app

I understand that this test assignment is based on the open-source app, but it would be so much better if Clue team could provide self-developed dummy app with similar functions.
The thing is that current open-source app has lots of unnecessary stuff, and I actually had trouble compiling it in it's initial form. 

It would be also cool if you could include versions for JDK, Gradle, Docker, and any other tools needed to compile the app in order to save candidates some time and makes their test assignment more fun.

Never forget that this test assignment is not only used by Clue team to verify candidate skills, it's also used by candidates and gives them a taste of their possible future tasks. 
