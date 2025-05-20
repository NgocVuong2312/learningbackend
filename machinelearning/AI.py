#object is to make AI that can help us cure pstd patients
#AI will be able to predict the patient's mental health condition
#data: None
#tools: None
#output: some text to help the patient
import openai
openai.api_key = "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"
def chat_gpt(prompt):
    response = openai.Completion.create(
      model="gpt-3.5-turbo",
      messages=[{"role": "user", "content":prompt}],
       
    )
    return response.choices[0].message.content.strip()
if __name__=="__main__":
    while True:
        user_input = input("You: ")
        if user_input.lower() in ['exit', 'quit']:
            break
        response = chat_gpt(user_input)
        print("AI:", response)