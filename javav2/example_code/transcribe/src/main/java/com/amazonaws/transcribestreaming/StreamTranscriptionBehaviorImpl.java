//snippet-sourcedescription:[StreamTranscriptionBehaviorImpl.java demonstrates how to implement StreamTranscriptionBehavior.]
// snippet-service:[transcribe]
// snippet-keyword:[Java]
// snippet-sourcesyntax:[java]
// snippet-keyword:[Amazon Transcribe]
// snippet-keyword:[Code Sample]
// snippet-keyword:[TranscribeStreamingAsyncClient]
// snippet-keyword:[StartStreamTranscriptionResponse]
// snippet-sourcetype:[snippet]
// snippet-sourcedate:[2019-01-10]
// snippet-sourceauthor:[AWS]


/**
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * This file is licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License. A copy of
 * the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 */
// snippet-start:[transcribe.java-streaming-client-behavior-imp]
package com.amazonaws.transcribestreaming;

import software.amazon.awssdk.services.transcribestreaming.model.Result;
import software.amazon.awssdk.services.transcribestreaming.model.StartStreamTranscriptionResponse;
import software.amazon.awssdk.services.transcribestreaming.model.TranscriptEvent;
import software.amazon.awssdk.services.transcribestreaming.model.TranscriptResultStream;
import java.util.List;

public class StreamTranscriptionBehaviorImpl implements StreamTranscriptionBehavior {


    @Override
    public void onError(Throwable e) {
        System.out.println("=== Failure encountered ===");
        e.printStackTrace();
    }

    @Override
    public void onStream(TranscriptResultStream e) {
        List<Result> results = ((TranscriptEvent) e).transcript().results();
        if (results.size() > 0) {
            if (results.get(0).alternatives().size() > 0)
                if (!results.get(0).alternatives().get(0).transcript().isEmpty()) {
                    System.out.println(results.get(0).alternatives().get(0).transcript());
                }
        }
    }

    @Override
    public void onResponse(StartStreamTranscriptionResponse r) {

        System.out.println(String.format("=== Received initial response. Request Id: %s ===", r.requestId()));
    }

    @Override
    public void onComplete() {
        System.out.println("=== All records streamed successfully ===");
    }
}
// snippet-end:[transcribe.java-streaming-client-behavior-imp]
