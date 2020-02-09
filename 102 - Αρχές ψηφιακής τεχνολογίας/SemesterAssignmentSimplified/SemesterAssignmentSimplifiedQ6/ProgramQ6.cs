using System;
using System.Collections.Generic;
using System.Linq;

namespace SemesterAssignmentSimplifiedQ6
{
    class ProgramQ6
    {
        static void Main(string[] args)
        {            
            Console.Write("Enter function: ");
            var userFunction = Console.ReadLine();

            while (!IsValidLogicalFunction(userFunction.ToUpper()))
            {
                Console.Write("Invalid function. Each input needs to appear once and the available gates are AND, OR, NOT. Enter function: ");
                userFunction = Console.ReadLine();
            }

            var TruthTableThreeInputs = new List<string>()
            {
                "000", "001", "010", "011", "100", "101", "110", "111"
            };

            var functionOutcome = String.Empty;

            Console.WriteLine("Truth table is: \n");

            for (int index  = 0; index < TruthTableThreeInputs.Count(); index++)
            {
                Console.WriteLine(TruthTableThreeInputs[index] + " " + CalculateTruthTable(userFunction.ToUpper())[index]);
            }
        }

        static List<string> CalculateTruthTable(string userInput)
        {
            var truthTable = String.Empty;            
            var userInputToWords = new List<string>();
            var truthTableList = new List<string>();

            foreach (var word in userInput.Split(" "))
            {
                userInputToWords.Add(word);
            }

            if (userInputToWords.Count(w => w == "AND") > 1)
            {
                truthTable = "00000001";
            }
            if (userInputToWords.Count(w => w == "OR") > 1)
            {
                truthTable = "01111111";
            }
            if (userInput.IndexOf("AND") < userInput.IndexOf("OR"))
            {
                if (userInput.Contains("(") && userInput.IndexOf("(") != 0)
                {
                    truthTable = "00000111";
                }
                else
                {
                    truthTable = "01010111";
                }                
            }
            if (userInput.IndexOf("AND") > userInput.IndexOf("OR"))
            {
                if (userInput.Contains("(") && userInput.IndexOf("(") != 0)
                {
                    truthTable = "00011111";
                }
                else
                {
                    truthTable = "00010101";
                }
            }
            foreach (var character in truthTable)
            {
                truthTableList.Add(character.ToString());
            }
            return truthTableList;
        }

        static bool IsValidLogicalFunction(string userInput)
        {
            var userInputToWords = new List<string>();
            var listOfValidInputs = new List<string>()
            {
                "AND", "OR", "NOT", "A", "B", "C"
            };

            userInput = userInput.Replace("(", "").Replace(")", "");

            foreach (var word in userInput.Split(" "))
            {
                if (!listOfValidInputs.Contains(word))
                {
                    return false;
                }
                userInputToWords.Add(word);
            }

            if (userInputToWords.Count(a => a == "A") > 1 || userInputToWords.Count(b => b == "B") > 1 || userInputToWords.Count(c => c == "C") > 1)
            {
                return false;
            }
            return true;
        }
    }
}