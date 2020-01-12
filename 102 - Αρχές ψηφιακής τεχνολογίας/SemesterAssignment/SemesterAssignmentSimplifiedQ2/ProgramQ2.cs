using System;
using System.Collections.Generic;

namespace SemesterAssignmentSimplifiedQ2
{
    class ProgramQ2
    {
        static void Main(string[] args)
        {
            var truthTableInputCombination = new List<string>();
            var truthTableInputEqualsOne = new List<string>();
            var functionResult = String.Empty;            

            var combinations = new List<string>
            {
                "000", "001", "010", "011", "100", "101", "110", "111"
            };

            for (int value = 1; value < 9; value++)
            {
                Console.Write($"Enter Truth Table value #{value}: ");
                var userInput = Console.ReadLine();
                while (!IsValid(userInput))
                {
                    Console.Write($"Valid values are 0 and 1. Enter Truth Table value #{value}: ");
                    userInput = Console.ReadLine();
                }
                truthTableInputCombination.Add(userInput);

                if (userInput == "1")
                {
                    truthTableInputEqualsOne.Add(combinations[value - 1]);
                }
            }
            Console.Clear();
            Console.WriteLine($"Truth Table: " + String.Join("", truthTableInputCombination));


            foreach (var value in truthTableInputEqualsOne)
            {
                if (value.StartsWith("0"))
                {
                    functionResult += "(NOT A AND ";
                }
                if (value.StartsWith("1"))
                {
                    functionResult += "(A AND ";
                }
                if (value.Remove(1).StartsWith("0"))
                {
                    functionResult += " NOT B AND ";
                }
                if (value.Remove(1).StartsWith("1"))
                {
                    functionResult += "B AND ";
                }
                if (value.EndsWith("0"))
                {
                    functionResult += "NOT C) ";
                }
                if (value.EndsWith("1"))
                {
                    functionResult += "C) ";
                }
                functionResult += " OR ";
            }
            Console.WriteLine("\nFunction is: " + functionResult.Remove(functionResult.Length - 3));
            Console.ReadKey();
        }

        static bool IsValid(string userInput)
        {
            if (userInput == "0" || userInput == "1")
            {
                return true;
            }
            return false;
        }

    }
}
