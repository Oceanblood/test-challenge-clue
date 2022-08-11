# Task by Ilia Belchenko

## Used Tools

### [Test Orchestrator](https://developer.android.com/training/testing/instrumented-tests/androidx-test-libraries/runner#java) - for haveing fresh installed app on every launch


### [Hamkrest](https://github.com/npryce/hamkrest) for containing assertions

### [Espresso](https://developer.android.com/training/testing/espresso) to view Matchers, etc. However, Espresso is poorer due to not very good waitings/assertions, expectations, etc., or even such decisions may be absent 

## Pattern - Robot 

I really love this pattern. It allows you to have all in the right place but separated in the same time.
Why Robot? the answer is very simple - human readable code, wich allows every person not only even from the manual QA team, who even don't know how to wright tests, strart to wright them and understand what is tested and how


## Possible improvements

1. We can look at [Kakao](https://github.com/KakaoCup/Kakao) 
Really good framework with a bit different code style. It might be very useful and also might help to work with diferent android elements such as SeekBars for example . And [Kakao](https://github.com/KakaoCup/Kakao) is much more better to use rather then pure [Espresso](https://developer.android.com/training/testing/espresso)

2. Screenshot maker
We will definitely need it in our tests, for having more human readable output in tests. ScreenShots also will allow us to debug and define the problem in our tests

[Spoon](https://square.github.io/spoon/) - will also allows you to implement screenshots and launch tests in parallel 
or
[Allure reports integration](https://github.com/allure-framework/allure-kotlin)

3. I would also wanted to separate Data class and make it more easier to have. If we have LoginScope + Login Data class which will store data/strings etc for tests
4. Integration with test cases, to have more coverage understanding 
5. ### [Barista](https://github.com/AdevintaSpain/Barista) for some view assertions 
