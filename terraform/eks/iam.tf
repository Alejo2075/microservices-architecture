module "eks_iam_roles" {
  source  = "terraform-aws-modules/iam/aws//modules/eks"
  version = "5.5.0"

  cluster_name = module.eks.cluster_id

  tags = {
    "Environment" = "Development"
    "Project"     = "${var.project_name}"
  }
}
