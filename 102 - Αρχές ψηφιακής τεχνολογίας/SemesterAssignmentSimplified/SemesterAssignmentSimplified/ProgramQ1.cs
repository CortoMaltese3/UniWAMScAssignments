using System;
using System.Collections.Generic;

namespace SemesterAssignmentSimplified
{
    class ProgramQ1
    {
        static void Main(string[] args)
        {

            var truthTableInputCombination = new List<string>();
            var userCombination = new List<string>();
            var combinations = new List<string>
            {
                "000", "001", "010", "011", "100", "101", "110", "111"
            };

            for (int value = 1; value < 9; value++)
            {
                Console.Write($"Enter Truth Table value #{value}: ");
                var userInput = Console.ReadLine();
                while (!IsValidTruthTableValue(userInput))
                {
                    Console.Write($"Valid values are 0 and 1. Enter Truth Table value #{value}: ");
                    userInput = Console.ReadLine();
                }
                truthTableInputCombination.Add(userInput);
            }
            Console.Clear();
            for (int value = 1; value < 4; value++)
            {
                Console.Write($"Enter input combination value #{value}: ");
                var userInput = Console.ReadLine();
                while (!IsValidInput(userInput))
                {
                    Console.Write($"Valid values are 0 and 1. Enter input combination value #{value}: ");
                    userInput = Console.ReadLine();
                }
                userCombination.Add(userInput);
            }
            Console.Clear();
            if (userCombination.Contains("-1"))
            {                
                Console.WriteLine("Goodbye!");
                Console.ReadKey();
            }
            else
            {                
                Console.WriteLine($"Truth Table: {String.Join("",truthTableInputCombination)}");
                Console.WriteLine($"Input Combination: {String.Join("", userCombination)}");
                var index = combinations.FindIndex(u => u.Contains(String.Join("", userCombination)));
                Console.WriteLine($"\nThe function response is: {truthTableInputCombination[index]} ");
                Console.ReadKey();
            }
        }

        static bool IsValidTruthTableValue(string userInput)
        {
            if (userInput == "0" || userInput == "1")
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        static bool IsValidInput(string userInput)
        {
            if (userInput == "0" || userInput == "1" || userInput == "-1")
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
}