using System;
using System.Collections.Generic;
using System.Linq;

namespace SemesterAssignmentSimplifiedQ4
{
    class ProgramQ4
    {
        static void Main(string[] args)
        {
            Console.Write("Enter gate name: ");
            var gateName = Console.ReadLine();
            while (!IsValidGate(gateName.ToUpper()))
            {
                Console.Write("Invalid gate name. Valid gates are AND, OR, XOR, NAND, NOR, XNOR. Enter gate name: ");
                gateName = Console.ReadLine();
            }

            Console.Write("Enter number of inputs: ");
            var numberOfInputs = Console.ReadLine();
            while (!IsValidNumberInput(numberOfInputs))
            {
                Console.Write("Invalid number of inputs. Valid input is an integer between 2 and 10. Enter number of inputs: ");
                numberOfInputs = Console.ReadLine();
            }

            var listOfInputs = new List<string>();

            for (int input = 1; input <= Convert.ToInt32(numberOfInputs); input++)
            {
                Console.Write($"Enter name of Input {input}: ");
                var inputName = Console.ReadLine();
                while (!IsValidName(inputName))
                {
                    Console.Write($"Invalid input Name. Valid input is between 1 and 10 characters. Enter name of Input {input}: ");
                    inputName = Console.ReadLine();
                }
                listOfInputs.Add(inputName);
            }

            Console.Write("Enter name of Output: ");
            var outputName = Console.ReadLine();
            while (!IsValidName(outputName))
            {
                Console.Write($"Invalid input Name. Valid input is between 1 and 10 characters. Enter name of Output: ");
                outputName = Console.ReadLine();
            }

            Console.Write("Enter architecture name: ");
            var architectureName = Console.ReadLine();
            while (!IsValidName(architectureName))
            {
                Console.Write($"Invalid architecture name. Valid input is between 1 and 10 characters. Enter architecture name: ");
                architectureName = Console.ReadLine();
            }

            var entityName = String.Concat(gateName, "_GATE").ToUpper();
            Console.Clear();
            Console.WriteLine("The VHDL code of the gate is: \n");

            CreateVhdlCode(gateName.ToUpper(), entityName, architectureName, listOfInputs, outputName);
            Console.ReadKey();
        }

        static void CreateVhdlCode(string gateName, string entityName, string architectureName, List<string> inputNames, string outputName)
        {
            var entityDeclaration = $"ENTITY {entityName} IS\n\tPORT(\n";
            foreach (var name in inputNames)
            {
                entityDeclaration += "\t\t" + name + ": IN BIT;\n";
            }
            entityDeclaration += "\t\t" + outputName + ": OUT BIT);" + $"\nEND {entityName};";
            Console.Write(entityDeclaration);

            var architectureBody = $"\n\nARCHITECTURE {architectureName} OF {entityName} IS\nBEGIN\n\t";
            architectureBody += $"{outputName} <= ";

            foreach (var name in inputNames)
            {
                architectureBody += $"{name} {gateName} ";
            }
            architectureBody = architectureBody.Remove(architectureBody.Length - gateName.Length - 2);
            architectureBody += $";\nEND {architectureName};";
            Console.Write(architectureBody);
        }

        static bool IsValidGate(string userInput)
        {
            var listOfGates = new List<string>()
            {
                "AND", "OR", "XOR", "NAND", "XNOR", "NOR"
            };

            if (listOfGates.Contains(userInput))
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        static bool IsValidNumberInput(string userInput)
        {
            try
            {
                var testUserInput = Convert.ToInt32(userInput);
                if (testUserInput < 2 || testUserInput > 10)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            catch (Exception)
            {
                return false;
            }
        }

        static bool IsValidName(string userInput)
        {
            if (userInput.Length < 1 || userInput.Length > 10)
            {
                return false;
            }
            return true;
        }
    }
}
