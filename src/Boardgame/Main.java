package Boardgame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static final int COL_NAME = 1;
    public static final int COL_YEAR = 2;
    public static final int COL_MIN = 6;
    public static final int COL_MAX = 7;

    public static final String[] LABELS = 
    { "30 - 60", "60 - 120", "120 - 180", "180 -"};
    
    public static void main(String[] args) throws Exception {
        if(args.length <= 0) {
            System.err.println("Missing Boardgame CSV");
            System.exit(1);
        }

        System.out.printf("Processing %s\n",args[0]);

        try (FileReader fr= new FileReader(args[0])){
                BufferedReader br = new BufferedReader(fr);
                Map<Integer, List<Boardgame>> catergorized =
                br.lines()
                .skip(1)
                .map(line->line.trim().split(","))
                .map(columns -> new Boardgame(columns[COL_NAME]   
                    , columns[COL_YEAR] 
                    ,Integer.parseInt(columns[COL_MIN]
                    ,Integer.parseInt(columns[COL_MAX])), 0))
                .collect(Collectors.groupingBy(game -> {
                    int dur = game.getDuration();

                    if ((dur >= 30) && (dur <60))
                        return 1;
                    else if ((dur >= 60)&& (dur<120))
                        return 2;
                    else if ((dur >= 120)&& (dur<180))
                        return 6;
                    return 7;
                    
                }))
                ;

                for (int i=0; i< LABELS.length; i++) {
                    System.out.printf("Duration: %s mins\n", LABELS[i]);
                    for (Boardgame bg: catergorized.get(i))
                        System.out.printf(" %s\n",bg.getName(), bg.getYear());
                }
                    
                
            

        }


    }
    
}
