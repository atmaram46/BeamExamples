package com.beam.core;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;

import java.util.Arrays;
import java.util.List;

public class BeamFilterExample {

    public static void main(String[] args) {
        PipelineOptions options = PipelineOptionsFactory.create();
        Pipeline pipeline = Pipeline.create(options);

        List<Double> gPrice = Arrays.asList(137.6, 140.1, 138.9, 144.0, 142.2, 139.1, 138.2, 141.1);

        pipeline.apply(Create.of(gPrice))
                .apply(ParDo.of(new FilterFn(140)));
        pipeline.run();
    }

    public static class FilterFn extends DoFn<Double, Double> {
        private double threshold = 0;

        public FilterFn(double threshold) {
            this.threshold = threshold;
        }

        @ProcessElement
        public void processElement(ProcessContext c) {
            if(c.element() > threshold) {
                c.output(c.element());
            }
        }
    }
}
