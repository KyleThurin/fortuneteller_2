import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FortuneTeller_v2 {

    //Creates a map of past questions and answers
    private static final Map<String, String> PAST_QUESTIONS_AND_ANSWERS = new HashMap<>();

    //Indexes an instance of the 'Random' number generator
    private static final Random RANDOM = new Random();

    //Creates a list of positive "yes" answers
    private static final String[] YES_RESPONSES = {"It is certain.",
            "Without a doubt.",
            "I guarantee it!",
            "As I see it, yes.",
            "Most likely, yes?",
            "Outlook good.",
            "Yes.",
            "I may, or may NOT, have been paid to say 'yes'...... so 'yes'!",
            "Signs point north, but I point YES!."};

    //Creates a list of negative "no" answers
    private static final String[] NO_RESPONSES = {"Outlook is hazy, try again later when I have more time...",
            "... I suggest finding a new hobby",
            "Better not tell you now.",
            "Cannot predict now.",
            "Concentrate and ask... someone else",
            "Don't count on it.",
            "The universe is telling me, with absolute certainty, a 100%, without a doubt... NO!",
            "My sources say no.",
            "Outlook not so good.",
            "Nope",
            "Very doubtful."};

    //Creates a list of custom responses
    private static final String[] OTHER_RESPONSES = {"The future is unclear.",
            "Time will tell.",
            "You need to pay the programmer more before I can answer that question...",
            "That is a matter of perspective.",
            "The stars are not (or not  yet?) aligned.",
            "Focus and ask again.",
            "Interesting question... NEXT!!!",
            "Let me get back to you on that... NEXT!",
            "Patience you must have, my young Padawan."};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the all knowing fortune teller [version two-point-OH!]");

        while (true) {
            System.out.println("\nAsk me anything (or type 'quit' to exit):");
            String userQuestion = getUserQuestion(scanner).trim();

            //If 'quit' is entered by the user
            if (userQuestion.equalsIgnoreCase("quit")) {
                //It prints "Farewell" and ends the program
                System.out.println("Farewell!");
                break;
            }

            //If map of past questions and answers contains the current question asked
            if (PAST_QUESTIONS_AND_ANSWERS.containsKey(userQuestion)) {
                //Print the following
                System.out.println("I remember you asking that! My previous answer was: " +
                        PAST_QUESTIONS_AND_ANSWERS.get(userQuestion));

                //If the current question is not in the map of past questions
            } else {
                //Run the question through the 'generateResponse' method and index the response
                String response = generateResponse(userQuestion);
                //Print the response
                System.out.println("The Enhanced all knowing fortune teller says: " + response);
                //Add the question and answer to the map of past questions
                PAST_QUESTIONS_AND_ANSWERS.put(userQuestion, response);
            }
        }
        scanner.close();
    }

    private static String generateResponse(String question) {

        //If the question starts with "will", "can", "should", or "is"
        if (question.toLowerCase().startsWith("will") ||
                question.toLowerCase().startsWith("can") ||
                question.toLowerCase().startsWith("should") ||
                question.toLowerCase().startsWith("is")) {
            //It is probably a "yes/no" question

            //Index a randomly selected number from 0 to one less the length of the combined yes/no responses
            int randomIndex = RANDOM.nextInt(YES_RESPONSES.length + NO_RESPONSES.length);

            //If 'randomIndex' IS greater than the length of 'YES_RESPONSES'
            if (randomIndex < YES_RESPONSES.length) {
                //Return a positive "yes" answer
                return YES_RESPONSES[randomIndex];

                //If 'randomIndex' is NOT greater than the length of 'YES_RESPONSES'
            } else {
                //Return a negative "no" answer
                return NO_RESPONSES[randomIndex - YES_RESPONSES.length];
            }
            //If the question starts with "what", "how", "when", or "where"
        } else if (question.toLowerCase().startsWith("what") ||
                question.toLowerCase().startsWith("how") ||
                question.toLowerCase().startsWith("when") ||
                question.toLowerCase().startsWith("where")) {
            //It's probably an open-ended question
            int randomIndex = RANDOM.nextInt(OTHER_RESPONSES.length);
            //Return a custom response
            return OTHER_RESPONSES[randomIndex];

            //If the question starts with anything else
        } else {
            //The question is unclear and therefore returns an answer from 'OTHER_RESPONSES'
            //Randomly pick an answer from 'OTHER_RESPONSES'
            return OTHER_RESPONSES[RANDOM.nextInt(OTHER_RESPONSES.length)];
        }
    }

    private static String getUserQuestion(Scanner scanner) {
        return scanner.nextLine();
    }
}