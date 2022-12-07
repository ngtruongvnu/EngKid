package com.example.engkit.fragment.vocabulary;

public class Vocabulary {
    public String vocabularyName;
    public String vocabularyMeaning;

    public Vocabulary(String vocabularyName, String vocabularyMeaning) {
        this.vocabularyName = vocabularyName;
        this.vocabularyMeaning = vocabularyMeaning;
    }

    public void setVocabularyName(String vocabularyName) {
        this.vocabularyName = vocabularyName;
    }

    public void setVocabularyMeaning(String vocabularyMeaning) {
        this.vocabularyMeaning = vocabularyMeaning;
    }

    public String getVocabularyName() {
        return vocabularyName;
    }

    public String getVocabularyMeaning() {
        return vocabularyMeaning;
    }

    @Override
    public String toString() {
        return "Vocabulary{" +
                "vocabularyName='" + vocabularyName + '\'' +
                ", vocabularyMeaning='" + vocabularyMeaning + '\'' +
                '}';
    }
}

