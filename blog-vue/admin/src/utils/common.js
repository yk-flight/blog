// 表单重置
export function resetForm (refName) {
  if (this.$refs[refName]) {
    this.$refs[refName].clearValidate()
    this.$refs[refName].resetFields()
  }
}
