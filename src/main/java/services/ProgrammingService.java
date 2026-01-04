package services;

import base.APIControlActions;
import entity.programmingPOJO.ProgrammingPayload;
import io.restassured.response.Response;
import utility.JavaToJSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProgrammingService extends APIControlActions {

    public Response submitProgrammingAnswer(String candidateScreeningId, String questionId, String jobRoleId, String experience, String jobApplicationId, String screeningTestId) {
        String payload = buildProgrammingAnswerPayload(questionId, jobRoleId, experience, jobApplicationId, screeningTestId);

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        setHeaders(headers);

        setBody(payload);
        return executePatchAPI("/candidateScreening/update-candidate-result/" + candidateScreeningId);
    }

    private String buildProgrammingAnswerPayload(String questionId, String jobRoleId, String experience, String jobApplicationId, String screeningTestId) {
        ProgrammingPayload.PasteAnalysis pasteAnalysis = ProgrammingPayload.PasteAnalysis.builder()
                .totalPasteEvents(0)
                .totalPastedCharacters(0)
                .pastePercentage(0)
                .rawPastePercentage(0)
                .largestPaste(0)
                .hasCodePatterns(false)
                .hasFormatting(false)
                .riskLevel("low")
                .build();

        ProgrammingPayload.FocusAnalysis focusAnalysis = ProgrammingPayload.FocusAnalysis.builder()
                .totalFocusEvents(0)
                .focusLossCount(0)
                .focusChangeFrequency(0)
                .riskLevel("low")
                .build();

        ProgrammingPayload.QualityAnalysis qualityAnalysis = ProgrammingPayload.QualityAnalysis.builder()
                .wordCount(390)
                .averageWordsPerMinute(343.28)
                .hasProperStructure(true)
                .hasVariedVocabulary(false)
                .qualityScore(1)
                .riskLevel("high")
                .build();

        ProgrammingPayload.CopySourceDistribution copySourceDistribution = ProgrammingPayload.CopySourceDistribution.builder()
                .build();

        ProgrammingPayload.GlobalEventAnalysis globalEventAnalysis = ProgrammingPayload.GlobalEventAnalysis.builder()
                .globalCopyCount(0)
                .questionCopyCount(0)
                .externalInteractionCount(0)
                .hasQuestionCopying(false)
                .hasHighRiskCopying(false)
                .copySourceDistribution(copySourceDistribution)
                .suspiciousPatternCount(0)
                .riskLevel("low")
                .build();

        ProgrammingPayload.CopyPasteCorrelations copyPasteCorrelations = ProgrammingPayload.CopyPasteCorrelations.builder()
                .totalCorrelations(0)
                .questionPasteCount(0)
                .averageTimeBetween(0)
                .riskLevel("low")
                .build();

        ProgrammingPayload.TypingAnalysis innerTypingAnalysis = ProgrammingPayload.TypingAnalysis.builder()
//                .averageTypingSpeed(90.38)
                .typingBursts(0)
                .totalKeystrokes(5)
                .backspaceCount(2)
                .riskLevel("high")
                .build();

        ProgrammingPayload.TypingAnalysis typingAnalysis = ProgrammingPayload.TypingAnalysis.builder()
                .totalDuration(68166)
                .totalCharacters(6161)
                .keystrokeCount(5)
                .pasteEventCount(0)
                .copyEventCount(0)
                .focusLossCount(0)
                .pasteAnalysis(pasteAnalysis)
                .typingAnalysis(innerTypingAnalysis)
                .focusAnalysis(focusAnalysis)
                .qualityAnalysis(qualityAnalysis)
                .globalEventAnalysis(globalEventAnalysis)
                .copyPasteCorrelations(copyPasteCorrelations)
                .riskScore(25)
                .sessionId("typing_1766289753076_4vsscmczy")
                .analysisVersion("2.0-enhanced")
                .timestamp("2025-12-21T04:03:41.247Z")
                .privacyCompliant(true)
                .build();

        String candidateAnswer = "import java.util.ArrayList;\n" +
                "import java.util.Collections;\n" +
                "import java.util.List;\n" +
                "import java.util.Scanner;\n" +
                "import java.util.Comparator;\n" +
                "import java.util.stream.Collectors;\n\n" +
                "class Patient {\n" +
                "    int id;\n" +
                "    String name;\n" +
                "    int age;\n\n" +
                "    public Patient(int id, String name, int age) {\n" +
                "        this.id = id;\n" +
                "        this.name = name;\n" +
                "        this.age = age;\n" +
                "    }\n\n" +
                "    public String getName() {\n" +
                "        return name;\n" +
                "    }\n\n" +
                "    public int getAge() {\n" +
                "        return age;\n" +
                "    }\n" +
                "}\n\n" +
                "public class Solution {\n" +
                "    public static void main(String[] args) {\n" +
                "        Scanner scanner = new Scanner(System.in);\n\n" +
                "        int N = scanner.nextInt();\n" +
                "        List<Patient> patients = new ArrayList<>();\n" +
                "        for (int i = 0; i < N; i++) {\n" +
                "            int id = scanner.nextInt();\n" +
                "            String name = scanner.next();\n" +
                "            int age = scanner.nextInt();\n" +
                "            patients.add(new Patient(id, name, age));\n" +
                "        }\n\n" +
                "        int minAge = scanner.nextInt();\n" +
                "        scanner.close();\n\n" +
                "        // TODO: Implement the solution here\n" +
                "        // Filter patients by minAge and then sort them by name.\n" +
                "        // Print the names of the filtered and sorted patients, one per line.\n\n" +
                "        // Example placeholder for output:\n" +
                "        // List<Patient> filteredAndSortedPatients; // Populate this with your solution\n" +
                "        // for (Patient p : filteredAndSortedPatients) {\n" +
                "        //     System.out.println(p.getName());\n" +
                "        // }\n" +
                "    }\n" +
                "}";

        ProgrammingPayload payload = ProgrammingPayload.builder()
                .questionId(questionId)
                .type("programming")
                .skill("api")
                .jobRoleId(jobRoleId)
                .experience(experience)
                .jobApplicationId(jobApplicationId)
                .timeSpent(73)
                .fullScreenExitCount(0)
                .tabSwitchCount(0)
                .candidateAnswer(candidateAnswer)
                .typingAnalysis(typingAnalysis)
                .hasTypingAnalysis(true)
                .screeningTestId(screeningTestId)
                .programmingLanguageId(62)
                .editorEvents(new ArrayList<>())
                .retakes(1)
                .build();

        return JavaToJSON.convertToJSON(payload);
    }

}
