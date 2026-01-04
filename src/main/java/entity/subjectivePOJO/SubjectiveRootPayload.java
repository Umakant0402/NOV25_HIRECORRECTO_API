package entity.subjectivePOJO;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubjectiveRootPayload {
    public String questionId;
    public String type;
    public String skill;
    public String jobRoleId;
    public String experience;
    public String jobApplicationId;
    public Integer timeSpent;
    public Integer fullScreenExitCount;
    public Integer tabSwitchCount;
    public String candidateAnswer;
    public TypingAnalysis typingAnalysis;
    public Boolean hasTypingAnalysis;

    @Builder
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CopyPasteCorrelations {
        public Integer totalCorrelations;
        public Integer questionPasteCount;
        public Integer averageTimeBetween;
        public String riskLevel;
    }

    @Builder
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CopySourceDistribution {
    }

    @Builder
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class FocusAnalysis {
        public Integer totalFocusEvents;
        public Integer focusLossCount;
        public Double focusChangeFrequency;
        public String riskLevel;
    }

    @Builder
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class GlobalEventAnalysis {
        public Integer globalCopyCount;
        public Integer questionCopyCount;
        public Integer externalIntegereractionCount;
        public Boolean hasQuestionCopying;
        public Boolean hasHighRiskCopying;
        public CopySourceDistribution copySourceDistribution;
        public Integer suspiciousPatternCount;
        public String riskLevel;
    }

    @Builder
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class PasteAnalysis {
        public Integer totalPasteEvents;
        public Integer totalPastedCharacters;
        public Integer pastePercentage;
        public Integer rawPastePercentage;
        public Integer largestPaste;
        public Boolean hasCodePatterns;
        public Boolean hasFormatting;
        public String riskLevel;
    }

    @Builder
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class QualityAnalysis {
        public Integer wordCount;
        public Double averageWordsPerMinute;
        public Boolean hasProperStructure;
        public Boolean hasVariedVocabulary;
        public Integer qualityScore;
        public String riskLevel;
    }


    @Builder
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class TypingAnalysis {
        public Integer totalDuration;
        public Integer totalCharacters;
        public Integer keystrokeCount;
        public Integer pasteEventCount;
        public Integer copyEventCount;
        public Integer focusLossCount;
        public PasteAnalysis pasteAnalysis;
        public TypingAnalysis typingAnalysis;
        public FocusAnalysis focusAnalysis;
        public QualityAnalysis qualityAnalysis;
        public GlobalEventAnalysis globalEventAnalysis;
        public CopyPasteCorrelations copyPasteCorrelations;
        public Integer riskScore;
        public String sessionId;
        public String analysisVersion;
        public String timestamp;
        public Boolean privacyCompliant;
        public Double averageTypingSpeed;
        public Integer typingBursts;
        public Integer totalKeystrokes;
        public Integer backspaceCount;
        public String riskLevel;
    }
}