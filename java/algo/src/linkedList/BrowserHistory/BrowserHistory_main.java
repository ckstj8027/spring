package linkedList.BrowserHistory;

import java.util.LinkedList;

class BrowserHistory {
    private LinkedList<String> linkedList = new LinkedList<>();
    int index = 0;
    public BrowserHistory(String homepage) {
        linkedList.add(homepage);
    }

    public void visit(String url) {
        index++;
        linkedList.add(index, url);
        while(index < linkedList.size()-1){
            linkedList.removeLast();
        }
    }

    public String back(int steps) {
        if(index-steps < 0){
            index = 0;
            return linkedList.getFirst();
        }
        index -= steps;
        return linkedList.get(index);
    }

    public String forward(int steps) {
        if(index + steps >= linkedList.size()){
            index = linkedList.size() - 1;

            return linkedList.getLast();
        }
        index += steps;
        return linkedList.get(index);
    }
}
public class BrowserHistory_main {
    public static void main(String[] args) {
        // 새로운 브라우저 히스토리 생성
        BrowserHistory browserHistory = new BrowserHistory("https://www.homepage.com");

        // 몇 개의 페이지를 방문
        browserHistory.visit("https://www.page1.com");
        browserHistory.visit("https://www.page2.com");
        browserHistory.visit("https://www.page3.com");

        // 현재 페이지 확인
        System.out.println("현재 페이지: " + browserHistory.forward(0)); // https://www.page3.com

        // 뒤로 가기
        System.out.println("이전 페이지: " + browserHistory.back(2)); // https://www.page1.com

        // 앞으로 가기
        System.out.println("다음 페이지: " + browserHistory.forward(1)); // https://www.page2.com
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */