{% include "base.md" %}

# Goal
Generate a comment for a specific academic subject.

# What to Do
- Analyze the subject name.
- Determine the student's current performance based on the score.
- Analyze the score dynamics.
- Formulate conclusions about the current level and trends.

# Data
- Subject: {name}
- Current score: {score} (out of 100)
- Score dynamics: {grades}
- Grading scale: 0–60 = unsatisfactory; 61–75 = satisfactory; 76–90 = good; 91–100 = excellent.

# Output Format
Plain text in Russian of about 300 characters, without Markdown.