interface ModerationRule {
    int apply(int marks);
}

class SimpleModeration implements ModerationRule {
    public int apply(int marks) {
        return marks + 5;
    }
}

abstract class Evaluation {
    ModerationRule rule;
    Evaluation(ModerationRule rule) {
        this.rule = rule;
    }

    void evaluate() {
        int theory = 70;
        int lab = 30;
        int total = calculate(theory, lab);
        total = rule.apply(total);
        grade(total);
    }

    abstract int calculate(int theory, int lab);
    abstract void grade(int total);
}

class BTech extends Evaluation {

    BTech(ModerationRule rule) {
        super(rule);
    }

    int calculate(int theory, int lab) {
        return theory + lab;
    }

    void grade(int total) {
        System.out.println("BTech: " + (total >= 50 ? "PASS" : "FAIL"));
    }
}

class MCA extends Evaluation {
    MCA(ModerationRule rule) {
        super(rule);
    }

    int calculate(int theory, int lab) {
        return (theory + lab) / 2;
    }

    void grade(int total) {
        System.out.println("MCA: " + (total >= 60 ? "PASS" : "FAIL"));
    }
}

public class EvaluationModel {
    public static void main(String[] args) {
        Evaluation e1 = new BTech(new SimpleModeration());
        e1.evaluate();
        Evaluation e2 = new MCA(new SimpleModeration());
        e2.evaluate();
    }
}