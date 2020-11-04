/* Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
SPDX-License-Identifier: Apache-2.0

ABOUT THIS NODE.JS EXAMPLE: This example works with AWS SDK for JavaScript version 3 (v3),
which is pending release.  The preview version of the SDK is available
at https://github.com/aws/aws-sdk-js-v3.

Purpose:
transcribe_create_job.ts demonstrates how to create an Amazon Transcribe transcription job.

Inputs (replace in code):
- REGION
- JOB_NAME
- LANGUAGE_CODE
- SOURCE_FILE_FORMAT
- SOURCE_LOCATION

Running the code:
ts-node transcribe_create_job.ts
 */
// snippet-start:[transcribe.JavaScript.jobs.createJobsV3]
const {
  TranscribeClient,
  StartTranscriptionJobCommand,
} = require("@aws-sdk/client-transcribe");

const client = new TranscribeClient({ region: "REGION" });
const params = {
  TranscriptionJobName: "JOB_NAME",
  LanguageCode: "LANGUAGE_CODE", // For example, 'en-US'
  MediaFormat: "SOURCE_FILE_FORMAT", // For example, 'wav'
  Media: {
    MediaFileUri: "SOURCE_LOCATION",
    // For example, "https://transcribe-demo.s3-REGION.amazonaws.com/hello_world.wav"
  },
};

const run = async () => {
  try {
    const data = await client.send(new StartTranscriptionJobCommand(params));
    console.log("Success - put", data);
  } catch (err) {
    console.log("Error", err);
  }
};
run();
// snippet-end:[transcribe.JavaScript.jobs.createJobsV3]
