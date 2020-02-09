using System;
using System.Collections.Generic;
using System.Linq;
using System.IO;
using System.Text;

namespace SemesterAssignmentSimplifiedQ5
{
    class ProgramQ5
    {
        static void Main(string[] args)
        {
            var inputNameList = new List<string>();

            for (int inputNumber = 1; inputNumber < 4; inputNumber++)
            {
                Console.Write($"Enter name of Input {inputNumber}: ");
                var userInput = Console.ReadLine();
                while (!IsValidName(userInput))
                {
                    Console.Write($"Invalid input name. Valid input is between 1 and 10 characters. Enter name of Input {inputNumber}:  ");
                    userInput = Console.ReadLine();
                }
                inputNameList.Add(userInput);
            }

            Console.Write("Enter name of Output: ");
            var functionOutput = Console.ReadLine();
            while (!IsValidName(functionOutput))
            {
                Console.Write("Invalid Output name. Valid input is between 1 and 10 characters. Enter name of Output:  ");
                functionOutput = Console.ReadLine();
            }

            Console.Write("Enter entity name: ");
            var entityName = Console.ReadLine();
            while (!IsValidName(entityName))
            {
                Console.Write("Invalid entity name. Valid input is between 1 and 10 characters. Enter entity name: ");
                entityName = Console.ReadLine();
            }

            Console.Write("Enter architecture name: ");
            var architectureName = Console.ReadLine();
            while (!IsValidName(architectureName))
            {
                Console.Write("Invalid architecture name. Valid input is between 1 and 10 characters. Enter architecture name: ");
                architectureName = Console.ReadLine();
            }

            Console.Write("Enter logical function: ");
            var logicalFunction = Console.ReadLine();
            while (!IsValidLogicalFunction(logicalFunction, inputNameList))
            {
                Console.Write("Invalid logical function. Possible failure reasons are invalid name gates or invalid input names. Valid gates are AND, OR. Enter logical function: ");
                logicalFunction = Console.ReadLine();
            }

            Console.Write("\nThe VHDL code of the gate is: ");
            Console.WriteLine();

            var gateNames = GetGatesFromLogicalFunction(logicalFunction);

            CreateVhdlCode(gateNames, entityName, architectureName, inputNameList, functionOutput);

            Console.ReadKey();

        }


        static void CreateVhdlCode(List<string> gateNames, string entityName, string architectureName, List<string> inputNames, string outputName)
        {
            var entityDeclaration = $"ENTITY {entityName} IS\n\tPORT(\n";

            for (int i = 0; i < inputNames.Count(); i++)
            {
                entityDeclaration += "\t\t" + inputNames[i] + ": IN BIT;\n";
            }

            entityDeclaration += "\t\t" + outputName + ": OUT BIT);" + $"\nEND {entityName};";
            Console.Write(entityDeclaration);

            var architectureBody = $"\n\nARCHITECTURE {architectureName} OF {entityName} IS\nBEGIN\n\t";
            architectureBody += $"{outputName} <= ";

            for (int i = 0; i < inputNames.Count(); i++)
            {
                architectureBody += $"{inputNames[i]} {gateNames[i]} ";
            }

            architectureBody = architectureBody.Remove(architectureBody.Length - 2);
            architectureBody += $";\nEND {architectureName};";
           
            Console.Write(architectureBody);
            Console.WriteLine();
            WriteVhdlCodeToFile(architectureBody);
        }

        static bool IsValidName(string userInput)
        {
            if (userInput.Length < 1 || userInput.Length > 10)
            {
                return false;
            }
            return true;
        }

        static bool IsValidLogicalFunction(string userInput, List<string> listOfInputs)
        {
            var listOfValidGates = GetGatesFromLogicalFunction(userInput);

            userInput = userInput.Replace("(", "").Replace(")", "");

            foreach (var word in userInput.Split(" "))
            {
                if (!listOfValidGates.Contains(word.ToUpper()) && !listOfInputs.Contains(word))
                {
                    return false;
                }
            }
            return true;
        }

        static List<string> GetGatesFromLogicalFunction(string userInput)
        {
            var listOfGates = new List<string>();
            userInput = userInput.ToUpper();

            var listOfValidGates = new List<string>()
            {
                "AND", "OR", "XOR", "NAND", "XNOR", "NOR"
            };

            userInput = userInput.Replace("(", "").Replace(")", "");

            foreach (var word in userInput.Split(" "))
            {
                if (listOfValidGates.Contains(word))
                {
                    listOfGates.Add(word);
                }
            }
            //Added to help CreateVhdlCode. When the final function is printed, we got OutOfRangeException cause gateNames are always
            //one sort comparing to listOfInputs. Not optimal, still works!
            listOfGates.Add("");

            return listOfGates;
        }

        public static void WriteVhdlCodeToFile(string vhdlCode)
        {
            string fileName = @"circ1.vhd";
            string pathString = Path.Combine(Environment.CurrentDirectory, fileName);

            Console.WriteLine($"\n\nNew file {fileName} has been created at path:\n {pathString}\n");

            StreamWriter streamWriter = new StreamWriter(pathString);

            //Utilizing using instead of Open/Close file
            //Clears file contents if it exists
            using (streamWriter)
            {
                if (File.Exists(pathString))
                {
                    streamWriter.Write(String.Empty);
                }
                streamWriter.Write(vhdlCode);
            }
        }
    }
}
