package org.bigdata.analyser;

import org.apache.hadoop.io.Text;

import java.util.Objects;

public class IOperStationAnalyzer extends Analyzer {
    /**
     * Class constructor.
     */
    public IOperStationAnalyzer (String record) {
        analyse(record);
    }

    /**
     * Analyze to initialize analyzer.
     */
    private void analyse(String record) {
        String[] tokens = record.split(",");
        String station = tokens[1];
        String direction = tokens[4];

        if(Objects.equals(direction, "vers sortie") || Objects.equals(direction, "vers entree")) {
            super.analyzable = true;
            super.key = station;
            super.value = direction;
        } else {
            super.analyzable = false;
        }
    }

    public static Text produce(Iterable<Text> values) {
        int numberIn = 0;
        int numberOut = 0;

        for (Text value : values) {
            String direction = value.toString();
            if (Objects.equals(direction, "vers entree")) {
                numberIn++;
            } else {
                numberOut++;
            }
        }

        return new Text(numberIn + "," + numberOut);
    }
}