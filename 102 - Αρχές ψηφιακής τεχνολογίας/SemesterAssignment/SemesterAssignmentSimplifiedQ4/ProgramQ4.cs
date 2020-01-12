using System;
using System.Collections.Generic;
using System.Linq;

namespace SemesterAssignmentSimplifiedQ4
{
    class ProgramQ4
    {
        static void Main(string[] args)
        {            

            
        }

        public static bool IsValidGate(string userInput)
        {
            var listOfGates = new List<string>()
            {
                "AND", "OR", "XOR", "NAND", "XNOR", "NOR"
            };

            if (listOfGates.Contains(userInput.ToUpper()))
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        public static bool IsValidNumberInput(string userInput)
        {            
            if (userInput == "2" || userInput == "3" || userInput == "4")
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
