using System;
using System.Collections.Generic;
using System.Linq;

namespace SemesterAssignmentSimplifiedQ6
{
    class ProgramQ6
    {
        static void Main(string[] args)
        {
            //var function = "(A AND B) OR C";
            Console.WriteLine("Enter function: ");
            var userFunction = Console.ReadLine();

            while (!IsValidLogicalFunction(userFunction.ToUpper()))
            {
                Console.WriteLine("Invalid function. Each input needs to appear once and the available gates are AND, OR, NOT. Enter function: ");
                userFunction = Console.ReadLine();
            }

            var TruthTableTwoInputs = new List<string>()
            {
                "00", "01", "10", "11"
            };

            var TruthTableThreeInputs = new List<string>()
            {
                "000", "001", "010", "011", "100", "101", "110", "111"
            };


        }



        static void ConvertToTruthTable(string userInput)
        {
            var truthTable = new List<string>();
            var parenthesisValues = new List<string>();
            var words = new List<string>();

            foreach (var word in userInput)
            {

            }


            foreach (var character in userInput)
            {                
                if (userInput.Contains("("))
                {
                    var parenthesisStart = userInput.IndexOf("(");
                    var parenthesisEnd = userInput.IndexOf(")");
                    var parenthesisWords = userInput.Substring(parenthesisStart + 1, parenthesisEnd - parenthesisStart - 1).Split(" ");
                    var parenthesisList = new List<string>();

                    if (parenthesisWords.Contains("AND"))
                    {

                    }

                    foreach (var word in parenthesisWords)
                    {
                        parenthesisList.Add(word);
                    }

                    if (parenthesisList.Contains("AND"))
                    {

                         

                    }

                    //deals with the calculations in parenthesis
                    foreach (var item in parenthesisList)
                    {
                        parenthesisValues.Add(item.ToString());                        
                    }
                    if (parenthesisValues.Contains("AND"))
                    {

                    }
                }

            }
        }

        static bool IsValidLogicalFunction(string userInput)
        {
            var checkA = userInput.Replace("A", "*");
            if (checkA.Count(a => a == '*') > 1 || userInput.Count(b => b == 'B') > 1 || userInput.Count(c => c == 'C') > 1)
            {
                return false;
            }
            //if (listOfValidInputs.Contains(userInput.ToUpper()))
            //{
            //    return true;
            //}
            else
            {
                return true;
            }
        }

        static void CalculateFunction(string userInput, List<string> possibleInputs)
        {
            var subTruthTable = new List<string>();
            if (userInput.Contains("AND"))
            {
                foreach (var inputSet in possibleInputs)
                {
                    if (inputSet.Contains("0"))
                    {
                        subTruthTable.Add("0");
                    }
                    else
                    {
                        subTruthTable.Add("1");
                    }
                }
            }
            if (userInput.Contains("OR"))
            {

            }
            //if (userInput.Contains("NOT"))
            //{

            //}
        }
    }
}
