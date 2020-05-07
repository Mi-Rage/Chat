import chat.Main;
import org.hyperskill.hstest.dynamic.input.DynamicTestingMethod;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.TestedProgram;

public class MainTest extends StageTest<String> {

    private final String case1 = "User19356 sent message";
    private final String case1Answer = "User19356: message";

    private final String case2 = "User19756 left";
    private final String case2Answer = "";

    private final String case3 = "User1946 join";
    private final String case3Answer = "";

    private final String case4 = "Bobby connected";
    private final String case4Answer = "";

    private final String case5 = "Nirvana disconnected";
    private final String case5Answer = "";

    private final String case6 = "gLadiator sent sent me a mail, please!";
    private final String case6Answer = "gLadiator: sent me a mail, please!";

    private final String case7 = "Dog sent ";
    private final String case7Answer = "Dog:";

    @DynamicTestingMethod
    CheckResult test() {
        TestedProgram main = new TestedProgram(Main.class);
        main.start();
        if (!main.execute(case1).trim().equals(case1Answer))
            return CheckResult.wrong("User's sent message is not correct");

        if (!main.execute(case2).trim().equals(case2Answer))
            return CheckResult.wrong("You don't need output any when user left");

        if (!main.execute(case3).trim().equals(case3Answer))
            return CheckResult.wrong("You don't need output any when user join");

        if (!main.execute(case4).trim().equals(case4Answer))
            return CheckResult.wrong("You don't need output any when user connected");

        if (!main.execute(case5).trim().equals(case5Answer))
            return CheckResult.wrong("You don't need output any when user disconnected");

        if (!main.execute(case6).trim().equals(case6Answer))
            return CheckResult.wrong("User's sent message is not correct");

        if (!main.execute(case7).trim().equals(case7Answer))
            return CheckResult.wrong("User's sent message is not correct");

        return CheckResult.correct();
    }
}

