package services;

import base.APIControlActions;
import base.ScreeningControl;
import entity.subjectivePOJO.SubjectiveRootPayload;
import io.restassured.response.Response;
import utility.JavaToJSON;

import java.util.HashMap;
import java.util.Map;

public class SubjectiveService extends APIControlActions {


    public Response submitSubjectiveAnswer(String questionId, String experience, String candidateAnswer) {
        SubjectiveRootPayload payload =
                SubjectiveRootPayload.builder()
                        .questionId(questionId)
                        .type("subjective")
                        .skill("api")
                        .jobRoleId(ScreeningControl.jobRoleID)
                        .experience(experience)
                        .jobApplicationId(ScreeningControl.jobApplicationID)
                        .timeSpent(60)
                        .fullScreenExitCount(0)
                        .tabSwitchCount(0)
                        .candidateAnswer(candidateAnswer)
                        .typingAnalysis(
                                SubjectiveRootPayload.TypingAnalysis.builder()
                                        .totalDuration(54979)
                                        .totalCharacters(103)
                                        .keystrokeCount(119)
                                        .pasteEventCount(0)
                                        .copyEventCount(0)
                                        .focusLossCount(2)

                                        .pasteAnalysis(
                                                SubjectiveRootPayload.PasteAnalysis.builder()
                                                        .totalPasteEvents(0)
                                                        .totalPastedCharacters(0)
                                                        .pastePercentage(0)
                                                        .rawPastePercentage(0)
                                                        .largestPaste(0)
                                                        .hasCodePatterns(false)
                                                        .hasFormatting(false)
                                                        .riskLevel("low")
                                                        .build()
                                        )

                                        // Nested typingAnalysis block
                                        .typingAnalysis(
                                                SubjectiveRootPayload.TypingAnalysis.builder()
                                                        .averageTypingSpeed(1.87)
                                                        .typingBursts(0)
                                                        .totalKeystrokes(119)
                                                        .backspaceCount(2)
                                                        .riskLevel("low")
                                                        .build()
                                        )

                                        .focusAnalysis(
                                                SubjectiveRootPayload.FocusAnalysis.builder()
                                                        .totalFocusEvents(4)
                                                        .focusLossCount(2)
                                                        .focusChangeFrequency(0.5)
                                                        .riskLevel("low")
                                                        .build()
                                        )

                                        .qualityAnalysis(
                                                SubjectiveRootPayload.QualityAnalysis.builder()
                                                        .wordCount(15)
                                                        .averageWordsPerMinute(16.37)
                                                        .hasProperStructure(false)
                                                        .hasVariedVocabulary(true)
                                                        .qualityScore(1)
                                                        .riskLevel("low")
                                                        .build()
                                        )

                                        .globalEventAnalysis(
                                                SubjectiveRootPayload.GlobalEventAnalysis.builder()
                                                        .globalCopyCount(0)
                                                        .questionCopyCount(0)
                                                        .externalIntegereractionCount(0)
                                                        .hasQuestionCopying(false)
                                                        .hasHighRiskCopying(false)
                                                        .copySourceDistribution(
                                                                SubjectiveRootPayload.CopySourceDistribution.builder().build()
                                                        )
                                                        .suspiciousPatternCount(0)
                                                        .riskLevel("low")
                                                        .build()
                                        )

                                        .copyPasteCorrelations(
                                                SubjectiveRootPayload.CopyPasteCorrelations.builder()
                                                        .totalCorrelations(0)
                                                        .questionPasteCount(0)
                                                        .averageTimeBetween(0)
                                                        .riskLevel("low")
                                                        .build()
                                        )

                                        .riskScore(0)
                                        .sessionId("typing_1766289626529_41cvu873s")
                                        .analysisVersion("2.0-enhanced")
                                        .timestamp("2025-12-21T04:01:21.509Z")
                                        .privacyCompliant(true)
                                        .build()
                        )
                        .hasTypingAnalysis(true)
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
