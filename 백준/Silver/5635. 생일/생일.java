import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int maxYear = 1989;
    static int maxMonth = 0;
    static int maxDay = 0;
    
    static int minYear = 2011;
    static int minMonth = 13;
    static int minDay = 32;
	
    static StringBuilder sb1 = new StringBuilder();
    static StringBuilder sb2 = new StringBuilder();
    
    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        for(int i = 0 ; i < n ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int Day = Integer.parseInt(st.nextToken());
            int Month = Integer.parseInt(st.nextToken());
            int Year = Integer.parseInt(st.nextToken());
            
            // 가장 어린 사람
            if(Year >= maxYear) {
                if(Year > maxYear) {
                	setYoung(name, Year, Month, Day);
                    continue;
                }
                else if(Month >= maxMonth) {
                    if(Month > maxMonth) {
                    	setYoung(name, Year, Month, Day);
                    	continue;
                    }
                    else if(Day > maxDay) {
                    	setYoung(name, Year, Month, Day);
                    	continue;
                    }
                }
            }
            
            //가장 나이많은 사람
            if(Year <= minYear) {
            	if(Year < minYear) {
                	setOld(name, Year, Month, Day);
                	continue;
            	}
            	else if(Month <= minMonth) {
            		if(Month < minMonth) {
            			setOld(name, Year, Month, Day);
                    	continue;
            		}
            		else if(Day < minDay) {
            			setOld(name, Year, Month, Day);
                    	continue;
                    }
                }
            }
            
            
        }
        
        System.out.println(sb1);
        System.out.println(sb2);
    }


	private static void setOld(String name, int year, int month, int day) {
		sb2.setLength(0);
        sb2.append(name);
        minYear = year;
        minMonth = month;
        minDay = day;
	}


	private static void setYoung(String name, int year, int month, int day) {
		sb1.setLength(0);
        sb1.append(name);
        maxYear = year;
        maxMonth = month;
        maxDay = day;
	}

}