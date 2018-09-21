using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AVL_Tree
{
    class AVL
    {
        class Node
        {
            public int data;
            public Node left;
            public Node right;
            public Node(int data)
            {
                this.data = data;
            }
        }

        Node root;

        public AVL() {
        }

        public void Add(int data) {
            Node newItem = new Node(data);
            if (root == null)
                root = newItem;
            else
                root = RecursiveInsert(root, newItem);
        }

        private Node RecursiveInsert(Node current, Node n) {
            if (current == null)
                return n;
            if (current.data > n.data) {
                current.left = RecursiveInsert(current.left, n);
                current = Balance_Tree(current);
            }
            if (current.data < n.data) {
                current.right = RecursiveInsert(current.right, n); 
                current = Balance_Tree(current);
            }
            return current;
        }

        private Node Balance_Tree(Node current) {
            if(Balance_Factor(current) > 1) {
                if(Balance_Factor(current.left) > 0) {
                    current = RotateLL(current);
                }
                else {
                    current = RotateLR(current);
                }
            }
            else if(Balance_Factor(current) < -1) {
                if(Balance_Factor(current.right) < 0) {
                    current = RotateRR(current);
                }
                else {
                    current = RotateRL(current);
                }
            }
            return current;
        }

        public void Find(int target) {
            if(Find(target, root).data == target)
                Console.WriteLine("{0} target was found", target);
            else
                Console.WriteLine("Sorry {0} target is not in the tree", target);
        }

        private Node Find(int target, Node current) {
            if (current.data == target)
                return current;
            else if (current.data > target)
                return Find(target, current.left);
            return Find(target, current.right);
        }

        public void DisplayTree()
        {
            if (root == null)
            {
                Console.WriteLine("Tree is empty");
                return;
            }
            InOrderDisplayTree(root);
            Console.WriteLine();
        }
        private void InOrderDisplayTree(Node current)
        {
            if (current != null)
            {
                InOrderDisplayTree(current.left);
                Console.Write("({0}) ", current.data);
                InOrderDisplayTree(current.right);
            }
        }

        public void Delete(int target) {
            root = Delete(root, target);
        }

        private Node Delete(Node current, int target) {
            Node parent;
            if (current == null)
                return null;

            if(current.data > target) {
                current.left = Delete(current.left, target);
                if(Balance_Factor(current) == -2) {
                    if (Balance_Factor(current.right) <= 0)
                        current = RotateRR(current);
                    else
                        current = RotateRL(current);
                }
            }

            else if(current.data < target) {
                current.right = Delete(current.right, target);
                if(Balance_Factor(current) == 2) {
                    if (Balance_Factor(current.left) >= 0)
                        current = RotateLL(current);
                    else
                        current = RotateLR(current);
                }
            }

            else {
                if (current.right != null)
                {
                    parent = current.right;
                    while (parent.left != null)
                        parent = parent.left;
                    current.data = parent.data;
                    current.right = Delete(current.right, parent.data);
                    if (Balance_Factor(current) == 2)
                    {
                        if (Balance_Factor(current.left) >= 0)
                            current = RotateLL(current);
                        else
                            current = RotateLR(current);
                    }
                }
                else
                    return current.left;
            }
            return current;
        }

        private int Max(int left, int right) {
            return (left > right) ? left : right;
        }

        private int GetHeight(Node current) {
            int height = 0;
            if(current != null) {
                int left = GetHeight(current.left);
                int right = GetHeight(current.right);
                int m = Max(left, right);
                height = m + 1;
            }
            return height;
        }

        private int Balance_Factor(Node current) {
            return GetHeight(current.left) - GetHeight(current.right);
        }

        private Node RotateRR(Node parent) {
            Node pivot = parent.right;
            parent.right = pivot.left;
            pivot.left = parent;
            return pivot;
        }

        private Node RotateLL(Node parent) {
            Node pivot = parent.left;
            parent.left = pivot.right;
            pivot.right = parent;
            return pivot;
        }

        private Node RotateLR(Node parent) {
            Node pivot = parent.left;
            parent.left = RotateRR(pivot);
            return RotateLL(parent);
        }

        private Node RotateRL(Node parent) {
            Node pivot = parent.right;
            parent.right = RotateRR(pivot);
            return RotateLL(parent);
        }
    }
}
