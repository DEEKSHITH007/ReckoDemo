# ReckoDemo
Family and Universe Problem
1. List of Families in a particular Universe
>> http://localhost:8000/family/family/{Universe_id}
Universe_id is a positive number between 1 to 4

2. Check if a family with given Family_id has the same power i.e if if the given family is balanced or not 
>>  http://localhost:8000/person/balance/{Family_id}
Family_id is any positive number 
Eg: http://localhost:8000/person/balance/2

3. Get the list of unbalanced Families 
>>  http://localhost:8000/person/unbalanced

4. Update the families to make them balances 
>>  http://localhost:8000/person/trybalance/{unbalanced_Family_id}
Unbalanced_Family_id is a positive number from result of above API
Eg: http://localhost:8000/person/trybalance/71
