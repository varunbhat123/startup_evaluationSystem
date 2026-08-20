package startup;

public class AIEvaluator {

    public static int innovationScore(String description) {

        int score = 50;

        description = description.toLowerCase();

        if(description.contains("ai")) score += 20;
        if(description.contains("machine learning")) score += 15;
        if(description.contains("blockchain")) score += 15;
        if(description.contains("iot")) score += 10;

        return Math.min(score,100);
    }

    public static int marketScore(String description) {

        int score = 50;

        description = description.toLowerCase();

        if(description.contains("health")) score += 20;
        if(description.contains("agriculture")) score += 15;
        if(description.contains("education")) score += 15;
        if(description.contains("fintech")) score += 20;

        return Math.min(score,100);
    }

    public static int technicalScore(String description) {

        int score = 60;

        description = description.toLowerCase();

        if(description.contains("cloud")) score += 10;
        if(description.contains("ai")) score += 10;
        if(description.contains("iot")) score += 10;

        return Math.min(score,100);
    }

    public static int financialScore(String description) {

        int score = 55;

        description = description.toLowerCase();

        if(description.contains("subscription")) score += 15;
        if(description.contains("saas")) score += 20;
        if(description.contains("marketplace")) score += 15;

        return Math.min(score,100);
    }
}