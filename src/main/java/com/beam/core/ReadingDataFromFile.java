package com.beam.core;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.values.KV;

import java.util.Arrays;
import java.util.List;

public class ReadingDataFromFile {

    public static String CSV_HEADER = "Id,Name,Phy,Chem,Math,Eng,Bio,His";

    public static void main(String[] args) {
        PipelineOptions options = PipelineOptionsFactory.create();
        Pipeline pipeline = Pipeline.create(options);

        pipeline.apply(TextIO.read().from("src/main/resources/StudentData.csv"))
                .apply(ParDo.of(new FilterFn(CSV_HEADER)))
                .apply(ParDo.of(new ComputeTotalScoreFn()))
                        .apply(ParDo.of(new ConvertToStringFn()))
                                .apply(TextIO.write().to("src/main/resources/StudentDataResult.csv"));
        //.apply(TextIO.write().to("path).withHeader("Name,Total").withNumShards(1));
        pipeline.run().waitUntilFinish();
    }

    public static class ConvertToStringFn extends DoFn<KV<String, Integer>, String> {
        @ProcessElement
        public void processElement(ProcessContext c) {
            c.output(c.element().getKey() + "," + c.element().getValue());
        }
    }

    public static class ComputeTotalScoreFn extends DoFn<String, KV<String, Integer>> {
        @ProcessElement
        public void processElement(ProcessContext c) {
            String[] data = c.element().split(",");
            String name = data[1];
            Integer score = Integer.parseInt(data[2]) + Integer.parseInt(data[3]) + Integer.parseInt(data[4])
                    + Integer.parseInt(data[5]) + Integer.parseInt(data[6]) + Integer.parseInt(data[7]);
            c.output(KV.of(name, score));
        }
    }

    public static class FilterFn extends DoFn<String, String> {
        private final String header;

        public FilterFn(String header) {
            this.header = header;
        }

        @ProcessElement
        public void processElement(ProcessContext c) {
            String row = c.element();
            if(!row.isEmpty() && !row.equals(this.header)) {
                c.output(c.element());
            }
        }
    }
}
