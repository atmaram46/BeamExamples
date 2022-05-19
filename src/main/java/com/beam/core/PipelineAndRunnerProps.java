package com.beam.core;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;

public class PipelineAndRunnerProps {

    public static void main(String[] args) {
        PipelineOptions options = PipelineOptionsFactory.create();
        System.out.println("Runner Name:" + options.getRunner().getName());
        System.out.println("Job Name:" + options.getJobName());
        System.out.println("Options Id:" + options.getOptionsId());
        System.out.println("Stable Unique Name:" + options.getStableUniqueNames());
        System.out.println("Temp Location:" + options.getTempLocation());
        System.out.println("User Agent:" + options.getUserAgent());
    }
}
