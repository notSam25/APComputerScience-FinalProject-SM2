package SortingAlgo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class BinarySearch implements Algo {
    private class searchInstance {

        public searchInstance(int yStart, int mid, int left, int right) {
            this.yStart = yStart;
            this.mid = mid;
            this.left = left;
            this.right = right;
        }

        public int yStart, mid, left, right;
    }

    // only stop when true
    private static boolean binarySearch() {
        if (left <= right) {
            mid = (int) Math.floor((left + right) / 2);
            if (arrayToSearch[mid] > targetNum) {
                right = mid - 1;
            } else {
                left = mid;
            }

            if (arrayToSearch[left] == targetNum) {
                return true;
            } else
                return false;
        }

        System.out.println("oopz");
        return false;
    }

    void renderBox(Graphics g, int y, int middle) {

        for (int i = 0; i < arrayToSearch.length; i++) {
            assert (i < arrayToSearch.length && i >= 0);

            int curNumber = arrayToSearch[i];

            g.setColor(Color.black);
            g.drawRect(150 + (boxWidth * i), y, boxWidth, boxHeight);

            if (curNumber == middle && middle == targetNum)
                g.setColor(Color.red);
            else if (curNumber == middle && middle != targetNum)
                g.setColor(Color.green);

            g.drawString("" + curNumber, 150 + (boxWidth * i) + (boxWidth * 1 / 3), y + 15);

        }
    }

    @Override
    public void onRender(Graphics graphics) {

        for (searchInstance si : searchInstances) {
            renderBox(graphics, si.yStart, arrayToSearch[si.mid]);
        }

        targetNum = 222;

        System.out.println(this.finishedSearch);

        if (binarySearch() && finishedSearch == false) {
            finishedSearch = true;
            System.out.println("added new solution");
            searchInstances.add(new searchInstance(250 + ((boxHeight + 5) * searchInstances.size()), mid, left, right));
        } else {
            searchInstances.add(new searchInstance(250 + ((boxHeight + 5) * searchInstances.size()), mid, left, right));
        }
    }

    private boolean finishedSearch = false;
    private ArrayList<searchInstance> searchInstances = new ArrayList<searchInstance>();

    // 10 numbers from 1-1000
    private static final int arrayToSearch[] = { 28, 124, 222, 352, 549, 568, 741, 791, 824, 968 },
            boxWidth = 50, boxHeight = 20;

    private static int mid = 0, left = 0, right = arrayToSearch.length - 1;
    private static int targetNum = arrayToSearch[(int) (Math.floor(Math.random() * arrayToSearch.length))];
}
