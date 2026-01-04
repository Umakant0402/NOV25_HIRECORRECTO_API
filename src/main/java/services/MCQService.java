package services;

import base.APIControlActions;
import base.ScreeningControl;
import entity.mcqPOJO.MCQRootPayload;
import io.restassured.response.Response;
import utility.JavaToJSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MCQService extends APIControlActions {


    public Response submitMCQAnswer(String questionId, String experience, String candidateAnswer) {
        MCQRootPayload.QuestionCopies questionCopies = MCQRootPayload.QuestionCopies.builder()
                .count(0)
                .averageLength(0)
                .timestamps(List.of())
                .build();

        MCQRootPayload.OptionCopies optionCopies = MCQRootPayload.OptionCopies.builder()
                .count(0)
                .averageLength(0)
                .timestamps(List.of())
                .build();

        MCQRootPayload.FullQuestionCopies fullQuestionCopies = MCQRootPayload.FullQuestionCopies.builder()
                .count(0)
                .averageLength(0)
                .timestamps(List.of())
                .build();


        MCQRootPayload payload = MCQRootPayload.builder()
                .questionId(questionId)
                .type("mcq")
                .skill("api")
                .jobRoleId(ScreeningControl.jobRoleID)
                .experience(experience)
                .jobApplicationId(ScreeningControl.jobApplicationID)
                .timeSpent(9)
                .fullScreenExitCount(0)
                .tabSwitchCount(0)
                .candidateAnswer(candidateAnswer)
                .copyPasteAnalysis(MCQRootPayload.CopyPasteAnalysis.builder()
                        .totalDuration(257)
                        .totalCopyEvents(0)
                        .optionCopyCount(0)
                        .fullQuestionCopyCount(0)
                        .hasQuestionCopying(false)
                        .hasOptionCopying(false)
                        .hasFullQuestionCopying(false)
                        .riskScore(0)
                        .riskLevel("low")
                        .isSuspicious(false)
                        .sessionId("copypaste_1766289737523_yq3hhq6i6")
                        .analysisVersion("2.0-mcq-focused")
                        .timestamp("2025-12-21T04:02:25.095Z")
                        .copyBreakdown(
                                MCQRootPayload.CopyBreakdown.builder()
                                        .questionCopies(questionCopies)
                                        .fullQuestionCopies(fullQuestionCopies)
                                        .optionCopies(optionCopies)
                                        .build())
                        .build()
                )
                .hasCopyPasteAnalysis(true)
                .build();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        setHeaders(headers);

        String jsonPayload = JavaToJSON.convertToJSON(payload);
        setBody(jsonPayload);

        return executePatchAPI("/api/candidateScreening/update-candidate-result/" + ScreeningControl.candidateScreeningId);
    }

}
