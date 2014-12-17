import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LotteryTest {

    protected Lottery solution;

    @Before
    public void setUp() {
        solution = new Lottery();
    }

    @Test(timeout = 2000)
    public void testCase0() {
        String[] rules = new String[]{"PICK ANY TWO: 10 2 F F", "PICK TWO IN ORDER: 10 2 T F", "PICK TWO DIFFERENT: 10 2 F T", "PICK TWO LIMITED: 10 2 T T"};

        String[] expected = new String[]{"PICK TWO LIMITED", "PICK TWO IN ORDER", "PICK TWO DIFFERENT", "PICK ANY TWO"};
        String[] actual = solution.sortByOdds(rules);

        Assert.assertArrayEquals(expected, actual);
    }

    @Test(timeout = 2000)
    public void testCase1() {
        String[] rules = new String[]{"INDIGO: 93 8 T F", "ORANGE: 29 8 F T", "VIOLET: 76 6 F F", "BLUE: 100 8 T T", "RED: 99 8 T T", "GREEN: 78 6 F T", "YELLOW: 75 6 F F"};

        String[] expected = new String[]{"RED", "ORANGE", "YELLOW", "GREEN", "BLUE", "INDIGO", "VIOLET"};
        String[] actual = solution.sortByOdds(rules);

        Assert.assertArrayEquals(expected, actual);
    }

    @Test(timeout = 2000)
    public void testCase2() {
        String[] rules = new String[]{};

        String[] expected = new String[]{};
        String[] actual = solution.sortByOdds(rules);

        Assert.assertArrayEquals(expected, actual);
    }

}
