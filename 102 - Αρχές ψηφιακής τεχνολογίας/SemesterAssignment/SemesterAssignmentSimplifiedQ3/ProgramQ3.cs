﻿using System;
using System.Collections.Generic;
using System.Linq;

namespace SemesterAssignmentSimplifiedQ3
{
    class ProgramQ3
    {
        static void Main(string[] args)
        {

            Console.Write("Enter Gate Name: ");
            var logicalGate = Console.ReadLine();
            while (!isValidGate(logicalGate))
            {
                Console.Write("Possible values are AND, OR, XOR, NAND, NOR, XNOR. Enter Gate Name: ");
                logicalGate = Console.ReadLine();
            }

            Console.Write("Enter number of inputs: ");
            var numberOfInputs = Console.ReadLine();
            while (!isValidNumberInput(numberOfInputs))
            {
                Console.WriteLine("Possible values are 2, 3, 4. Enter number of inputs");
                numberOfInputs = Console.ReadLine();
            }

            var TruthTable = new List<string>();

            var TruthTableTwoInputs = new List<string>()
            {
                "00", "01", "10", "11"
            };

            var TruthTableThreeInputs = new List<string>()
            {
                "000", "001", "010", "011", "100", "101", "110", "111"
            };

            var TruthTableFourInputs = new List<string>()
            {
                "0000", "0001", "0010", "0101", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101" , "1110", "1111"
            };


            if (logicalGate.ToUpper() == "AND")
            {
                if (numberOfInputs == "2")
                {
                    Console.WriteLine("Truth Table is: " + String.Join("", CalculateAFunction_AND(TruthTableTwoInputs)));
                }
                if (numberOfInputs == "3")
                {
                    Console.WriteLine("Truth Table is: " + String.Join("", CalculateAFunction_AND(TruthTableThreeInputs)));
                }
                if (numberOfInputs == "4")
                {
                    Console.WriteLine("Truth Table is: " + String.Join("", CalculateAFunction_AND(TruthTableFourInputs)));
                }
            }

            if (logicalGate.ToUpper() == "OR")
            {
                if (numberOfInputs == "2")
                {
                    Console.WriteLine("Truth Table is: " + String.Join("", CalculateFunction_OR(TruthTableTwoInputs)));
                }
                if (numberOfInputs == "3")
                {
                    Console.WriteLine("Truth Table is: " + String.Join("", CalculateFunction_OR(TruthTableThreeInputs)));
                }
                if (numberOfInputs == "4")
                {
                    Console.WriteLine("Truth Table is: " + String.Join("", CalculateFunction_OR(TruthTableFourInputs)));
                }
            }

            if (logicalGate.ToUpper() == "XOR")
            {
                if (numberOfInputs == "2")
                {
                    Console.WriteLine("Truth Table is: " + String.Join("", CalculateFunction_XOR(TruthTableTwoInputs)));
                }
                if (numberOfInputs == "3")
                {
                    Console.WriteLine("Truth Table is: " + String.Join("", CalculateFunction_XOR(TruthTableThreeInputs)));
                }
                if (numberOfInputs == "4")
                {
                    Console.WriteLine("Truth Table is: " + String.Join("", CalculateFunction_XOR(TruthTableFourInputs)));
                }
            }

            if (logicalGate.ToUpper() == "NAND")
            {
                if (numberOfInputs == "2")
                {
                    Console.WriteLine("Truth Table is: " + String.Join("", CalculateFunction_NAND(TruthTableTwoInputs)));
                }
                if (numberOfInputs == "3")
                {
                    Console.WriteLine("Truth Table is: " + String.Join("", CalculateFunction_NAND(TruthTableThreeInputs)));
                }
                if (numberOfInputs == "4")
                {
                    Console.WriteLine("Truth Table is: " + String.Join("", CalculateFunction_NAND(TruthTableFourInputs)));
                }
            }
            if (logicalGate.ToUpper() == "NOR")
            {
                if (numberOfInputs == "2")
                {
                    Console.WriteLine("Truth Table is: " + String.Join("", CalculateFunction_NOR(TruthTableTwoInputs)));
                }
                if (numberOfInputs == "3")
                {
                    Console.WriteLine("Truth Table is: " + String.Join("", CalculateFunction_NOR(TruthTableThreeInputs)));
                }
                if (numberOfInputs == "4")
                {
                    Console.WriteLine("Truth Table is: " + String.Join("", CalculateFunction_NOR(TruthTableFourInputs)));
                }
            }

                Console.ReadKey();
        }

        public static List<string> CalculateAFunction_AND(List<string> truthTable)
        {            
            var truthTableOutcome = new List<string>();
            foreach (string element in truthTable)
            {
                if (element.Contains("0"))
                {
                    truthTableOutcome.Add("0");
                }
                else
                {
                    truthTableOutcome.Add("1");
                }
            }
            return truthTableOutcome;
        }

        public static List<string> CalculateFunction_OR(List<string> truthTable)
        {
            var truthTableOutcome = new List<string>();
            foreach (string element in truthTable)
            {
                if (element.Contains("1"))
                {
                    truthTableOutcome.Add("1");
                }
                else
                {
                    truthTableOutcome.Add("0");
                }
            }
            return truthTableOutcome;
        }

        public static List<string> CalculateFunction_XOR(List<string> truthTable)
        {
            var truthTableOutcome = new List<string>();
            foreach (string element in truthTable)
            {
                if (element.Contains("1"))
                {
                    truthTableOutcome.Add("1");
                }
                else
                {
                    truthTableOutcome.Add("0");
                }
            }
            return truthTableOutcome;
        }

        public static List<string> CalculateFunction_NOR(List<string> truthTable)
        {
            var truthTableOutcome = new List<string>();
            foreach (string element in truthTable)
            {
                if (element.Contains("1"))
                {
                    truthTableOutcome.Add("1");
                }
                else
                {
                    truthTableOutcome.Add("0");
                }
            }
            return truthTableOutcome;
        }

        public static List<string> CalculateFunction_NAND(List<string> truthTable)
        {
            var truthTableOutcome = new List<string>();
            foreach (string element in truthTable)
            {
                if (element.Contains("1"))
                {
                    truthTableOutcome.Add("1");
                }
                else
                {
                    truthTableOutcome.Add("0");
                }
            }
            return truthTableOutcome;
        }

        public static bool isValidGate(string userInput)
        {
            var listOfGates = new List<string>()
            {
                "AND", "OR", "XOR", "NAND", "XNOR"
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

        public static bool isValidNumberInput(string userInput)
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

        //public static void CalculateTruthTable(string numberOfInputs)
        //{
        //    var listOfInputs = new List<string>();
        //    var num = Math.Pow(2, Convert.ToInt32(numberOfInputs));
        //    for (int i = 0; i < num; i++)
        //    {
                
        //    }
        //}

        //public static string LogicalGateCalculate(string userInput)
        //{

        //    if (logicalGate == "AND")
        //    {

        //    }
        //    if (logicalGate == "OR")
        //    {

        //    }
        //    if (logicalGate == "XOR")
        //    {

        //    }
        //    if (logicalGate == "NAND")
        //    {

        //    }
        //    if (logicalGate == "XNOR")
        //    {

        //    }


        //}
    }
}
