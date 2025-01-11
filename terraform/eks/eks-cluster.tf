module "eks" {
  source          = "terraform-aws-modules/eks/aws"
  cluster_name    = "${var.project_name}-eks-cluster"
  cluster_version = "1.27"
  vpc_id          = module.vpc.vpc_id
  subnets         = module.vpc.private_subnets

  node_groups = {
    eks_nodes = {
      desired_capacity = 2
      max_capacity     = 3
      min_capacity     = 1
      instance_type    = "t3.medium"
    }
  }

  tags = {
    "Environment" = "Development"
    "Project"     = "${var.project_name}"
  }
}
